package ru.geekbrains.SpringDataApp.entiries;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float prise;

    public Product() {
    }

    public Product(String name, Float prise) {
        this.name = name;
        this.prise = prise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrise() {
        return prise;
    }

    public void setPrise(Float prise) {
        this.prise = prise;
    }
}
