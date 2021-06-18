package app.models;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRepository {

    private final HashMap<Integer, Product> products;

    private int lastId = 0;

    public ProductRepository() {
        products = new HashMap<Integer, Product>();
        saveOrUpdate(new Product("товар_1", 50f));
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == 0) {
            product.setId(++lastId);
        }
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return products.values().stream().collect(Collectors.toList());
    }

    public Product findById(int id) {
        return products.get(id);
    }

    public Product findByName(String title) {
        for (Product p : products.values()) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                return p;
            }
        }
        return null;
    }

    public void remove(Product product) {
        products.remove(product.getId());
    }
}
