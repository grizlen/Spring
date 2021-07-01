package ru.geekbrains.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "from Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "cost")
    Float cost;

    public Product() {}

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Product: %d \"%s\" %.2f", id, title, cost);
    }
}
