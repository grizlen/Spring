package ru.geekbrains.SpringMarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private final List<Long> producIdtList;

    public Cart() {
        producIdtList = new ArrayList<>();
    }

    public List<Long> getProducts() {
        return Collections.unmodifiableList(producIdtList);
    }

    public Long AddProduct(Long productId) {
        producIdtList.add(productId);
        return productId;
    }

    public void deleteProduct(Long id) {
        producIdtList.remove(id);
    }
}
