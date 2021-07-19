package ru.geekbrains.SpringMarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.SpringMarket.model.Product;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String title;
    private Float price;

    public ProductDTO(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }
}
