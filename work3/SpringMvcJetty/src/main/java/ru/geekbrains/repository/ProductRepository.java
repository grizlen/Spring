package ru.geekbrains.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final HashMap<Integer, Product> products;
    private int lastId = 0;

    public ProductRepository() {
        products = new HashMap<>();
        add(new Product("AAA", 120f));
        add(new Product("BBB", 10.5f));
        add(new Product("CCC", 100f));
    }

    private boolean add(Product product) {
        if (product == null || product.getId() != 0) { return false;}
        product.setId(++lastId);
        products.put(lastId, product);
        return true;
    }

    public List<Product> findAll() {
        return products.values().stream().collect(Collectors.toList());
    }

    public Product findById(int id) {
        return products.get(id);
    }

    public void save(Product product) {
        if (product == null) {
            return;
        }
        if (product.getId() == 0) {
            add(product);
        } else {
            products.put(product.getId(), product);
        }
    }

    public void delete(int id) {
        products.remove(id);
    }
}
