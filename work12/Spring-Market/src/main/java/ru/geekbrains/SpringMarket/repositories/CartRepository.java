package ru.geekbrains.SpringMarket.repositories;

import ru.geekbrains.SpringMarket.model.CartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CartRepository {

    private final List<CartItem> cartItemList;

    public CartRepository() {
        cartItemList = new ArrayList<>();
    }

    public List<CartItem> getProducts() {
        return Collections.unmodifiableList(cartItemList);
    }

    public void addProduct(Long id, Integer count) {
        CartItem cartItem = cartItemList.stream().filter(c -> c.getProductId() == id).findFirst().orElseGet(() -> null);
        if (cartItem == null) {
            cartItem = new CartItem(id, 0);
            cartItemList.add(cartItem);
        }
        cartItem.setCount(cartItem.getCount() + count);
    }

    public void deleteProduct(Long id) {
        Optional<CartItem> cart = cartItemList.stream().filter(c -> c.getProductId() == id).findFirst();
        if (cart.isPresent()) {
            cartItemList.remove(cart.get());
        }
    }
}
