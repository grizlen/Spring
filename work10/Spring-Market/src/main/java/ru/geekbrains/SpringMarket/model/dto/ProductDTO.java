package ru.geekbrains.SpringMarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.repositories.CategoryRepository;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String title;
    private Float price;
    private String category;

//    @Autowired
//    private CategoryRepository categoryRepository;

    public ProductDTO(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
        category = product.getCategory() != null ? product.getCategory().getTitle() : "";
    }

//    public Product toProduct() {
//        Category cat = categryRepository.findByTitle(category);
//        Product product = new Product(title, price, cat);
//        product.setId(id);
//        return product;
//    }
}
