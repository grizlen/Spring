package app.services;

import app.models.Product;
import app.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product get(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            System.out.printf("Товар с id = %d не найден.\n", id);
            return null;
        }
        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public boolean addProduct(String title, float cost) {
        Product product = productRepository.findByName(title);
        if (product != null) {
            System.out.printf("Товар \"%s\" уже существует.\n", title);
            return false;
        }
        productRepository.saveOrUpdate(new Product(title,cost));
        return true;
    }

    public boolean rename(int id, String newName) {
        Product product = get(id);
        if (product == null) {
            return false;
        }
        product.setTitle(newName);
        productRepository.saveOrUpdate(product);
        return true;
    }

    public boolean recost(int id, float cost) {
        Product product = get(id);
        if (product == null) {
            return false;
        }
        product.setCost(cost);
        productRepository.saveOrUpdate(product);
        return true;
    }

    public boolean delete(int id) {
        Product product = get(id);
        if (product == null) {
            return false;
        }
        productRepository.remove(product);
        return true;
    }

    public int getCount() {
        return productRepository.findAll().size();
    }

    public float getAvg() {
        float sum = 0f;
        int count = 0;
        for (Product p : productRepository.findAll()) {
            sum += p.getCost();
            count++;
        }
        return count == 0 ? 0f : sum / count;
    }
}
