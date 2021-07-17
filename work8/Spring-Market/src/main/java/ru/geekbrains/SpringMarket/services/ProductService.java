package ru.geekbrains.SpringMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.repositories.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final int ITEMS_ON_PAGE = 5;

    @Autowired
    private final ProductRepository productRepository;

    public Page<Product> getAll(Float minPrice, Float maxPrice, int page) {
        if (minPrice == null && maxPrice == null) {
            return productRepository.findAll(PageRequest.of(page, ITEMS_ON_PAGE));
        } else {
            if (minPrice == null) {
                minPrice = 0f;
            }
            if (maxPrice == null) {
                return productRepository.findByPriceGreaterThanEqual(PageRequest.of(page, ITEMS_ON_PAGE), minPrice);
            } else return productRepository.findByPriceBetween(PageRequest.of(page, ITEMS_ON_PAGE), minPrice, maxPrice);
        }
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product add(String name, Float price) {
        return productRepository.save(new Product(name, price));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> like(String name, int page) {
        return productRepository.findAllByNameLike(PageRequest.of(page, ITEMS_ON_PAGE), name + "%");
    }

    public Product add(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product edit(Product product) {
        return productRepository.save(product);
    }
}
