package ru.geekbrains.SpringMarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, Float price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

}
