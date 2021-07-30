angular.module('marketApp', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost/market';

    $scope.fillTable = function(pg = 0) {
        $http.get(contextPath + '/api/v1/products', {
                params: {
                    min_price: $scope.filter ? $scope.filter.minPrice : null,
                    max_price: $scope.filter ? $scope.filter.maxPrice : null,
                    title: $scope.filter ? $scope.filter.title : null,
                    page: pg
                }
            })
            .then(function(response) {
                $scope.productPage = response.data;
                $scope.navPages = $scope.generateNavPagesIndexes(pg);
            });
    };

    $scope.generateNavPagesIndexes = function(pg) {
        result = [];
        startPg = pg - 2;
        if (startPg < 0) startPg = 0;
        endPg = pg + 2;
        if (endPg > $scope.productPage.totalPages - 1) endPg = $scope.productPage.totalPages - 1;
        for (i = startPg; i < endPg + 1; i++) result.push(i);
        return result;
    }

    $scope.addItem = function() {
        $scope.newProduct.category = 'Продукты';
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
        .then(function(response) {
            $scope.newProduct = null;
            $scope.fillTable($scope.productPage.totalPages - 1);
        });
    }

    $scope.deleteItem = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
        .then(function(response) {
            $scope.fillTable($scope.productPage.number);
            });
    }

    $scope.editItem = function (p) {
        $scope.editProdct = {id: p.id, title: p.title, price: p.price, category: 'Продукты'};
    };

    $scope.editOk = function () {
        $http.put(contextPath + "/api/v1/products", $scope.editProdct)
        .then(function(response) {
            $scope.editProdct = null;
            $scope.fillTable($scope.productPage.number);
            });
    };

    $scope.editCancel = function () {
        $scope.editProdct = null;
        $scope.fillTable($scope.productPage.number);
    };

    $scope.sendLogin = function () {
        $http.post(contextPath + "/api/v1/auth/login", $scope.auth)
        .then(function(response) {
            $scope.authResponse = response.data;
        });
    };

    $scope.sendRegister = function () {
        $http.post(contextPath + "/api/v1/auth/signup", $scope.auth)
        .then(function(response) {
            $scope.authResponse = response.data;
        });
    };

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart/all')
        .then(function(response) {
            $scope.cartOrders = response.data;
        });
    };

    $scope.addToCart = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
        .then(function(response) {
            $scope.showCart();
        });
    };

    $scope.deleteFromCart = function (id) {
        $http.delete(contextPath + '/api/v1/cart/' + id)
        .then(function(response) {
            $scope.showCart();
        });
    };

    $scope.fillTable();
    $scope.showCart();
});