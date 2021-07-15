package ru.geekbrains.SpringDataApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SpringDataApp.entiries.Product;
import ru.geekbrains.SpringDataApp.repositoryes.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(Float min, Float max) {
        if (min == null && max == null) {
            return productRepository.findAll();
        } else {
            if (min == null) {
                min = 0f;
            }
            if (max == null) {
                return productRepository.findByPriseGreaterThanEqual(min);
            } else return productRepository.findByPriseBetween(min, max);
        }
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product add(String name, Float prise) {
        return productRepository.save(new Product(name, prise));
    }

    @Transactional
    public List<Product> delete(Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    public List<Product> like(String name) {
        return productRepository.findAllByNameLike(name + "%");
    }
}
