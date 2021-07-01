package ru.geekbrains.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.SpringBoot.model.Product;
import ru.geekbrains.SpringBoot.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    public String  saveProduct(Product product) {
        productRepository.save(product);
        return "products";
    }

    public void deleteProduct(int id) {
        productRepository.delete(id);
    }
}
