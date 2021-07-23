package ru.geekbrains.SpringMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.SpringMarket.model.Category;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.model.dto.ProductDTO;
import ru.geekbrains.SpringMarket.repositories.CategoryRepository;
import ru.geekbrains.SpringMarket.repositories.ProductRepository;
import ru.geekbrains.SpringMarket.repositories.specifications.ProductSpecifications;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final int ITEMS_ON_PAGE = 5;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<ProductDTO> findAll(MultiValueMap<String, String> params, int page, int pageSize) {
        if (page < 0) {
            page = 0;
        }
        Specification<Product> spec = ProductSpecifications.build(params);
        PageRequest pr = PageRequest.of(page, pageSize < ITEMS_ON_PAGE ? ITEMS_ON_PAGE : pageSize);
        return productRepository.findAll(spec, pr).map(ProductDTO::new);
    }

    public Optional<ProductDTO> getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return Optional.ofNullable(new ProductDTO(product.get()));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product fromProductDTO(ProductDTO dto) {
        Category cat = categoryRepository.findByTitle(dto.getCategory());
        Product product = new Product(dto.getTitle(), dto.getPrice(), cat);
        product.setId(dto.getId());
        return product;
    }

    public ProductDTO add(ProductDTO product) {
        product.setId(null);
        return new ProductDTO(productRepository.save(fromProductDTO(product)));
    }

    public ProductDTO edit(ProductDTO product) {
        return new ProductDTO(productRepository.save(fromProductDTO(product)));
    }
}
