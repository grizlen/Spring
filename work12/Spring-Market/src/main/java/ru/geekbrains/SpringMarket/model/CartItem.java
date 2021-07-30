package ru.geekbrains.SpringMarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    private Long productId;
    private Integer count;

    public CartItem(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

}
