package ru.geekbrains.SpringMarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private Long productId;
    private Integer count;

    public Cart(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

}
