package ru.geekbrains.HiberSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.HiberSpringApp.entities.Client;
import ru.geekbrains.HiberSpringApp.entities.Product;
import ru.geekbrains.HiberSpringApp.repositories.ProductRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository products;

    public ProductService(@Autowired ProductRepository products) {
        this.products = products;
    }

    public List<Product> allProducts() {
        return products.findAll();
    }

    public Product newProduct(String name, Float cost) {
        Product product = new Product(name, cost);
        products.saveOrUpdate(product);
        return product;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public List<Client> getProductClients(Product product) {
        product = products.get(product.getId());
        return product != null ? product.getClients() : Collections.emptyList();
    }
}
