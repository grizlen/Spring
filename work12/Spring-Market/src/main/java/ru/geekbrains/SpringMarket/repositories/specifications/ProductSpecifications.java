package ru.geekbrains.SpringMarket.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.SpringMarket.model.Product;

public class ProductSpecifications {
    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                            root.get("price"), params.getFirst("min_price"))
            );
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), params.getFirst("max_price"))
            );
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                            root.get("title"), String.format("%%%s%%", params.getFirst("title")))
            );
        }
        return spec;
    }
}
