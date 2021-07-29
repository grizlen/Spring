package ru.geekbrains.SpringMarket.repositories;

import ru.geekbrains.SpringMarket.model.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CartRepository {

    private final List<Cart> cartList;

    public CartRepository() {
        cartList = new ArrayList<>();
    }

    public List<Cart> getProducts() {
        return Collections.unmodifiableList(cartList);
    }

    public void addProduct(Long id, Integer count) {
        Cart cart = cartList.stream().filter(c -> c.getProductId() == id).findFirst().orElseGet(() -> null);
        if (cart == null) {
            cart = new Cart(id, 0);
            cartList.add(cart);
        }
        cart.setCount(cart.getCount() + count);
    }

    public void deleteProduct(Long id) {
        Optional<Cart> cart = cartList.stream().filter(c -> c.getProductId() == id).findFirst();
        if (cart.isPresent()) {
            cartList.remove(cart.get());
        }
    }
}
