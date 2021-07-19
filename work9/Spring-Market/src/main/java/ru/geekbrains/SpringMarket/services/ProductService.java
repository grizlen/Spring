package ru.geekbrains.SpringMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.repositories.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final int ITEMS_ON_PAGE = 5;

    @Autowired
    private final ProductRepository productRepository;

    public Page<Product> findAll(Specification<Product> spec, int page, int pageSize) {
        if (page < 0) {
            page = 0;
        }
        return productRepository.findAll(spec, PageRequest.of(page, pageSize < ITEMS_ON_PAGE ? ITEMS_ON_PAGE : pageSize));
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

    public Product add(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product edit(Product product) {
        return productRepository.save(product);
    }
}
