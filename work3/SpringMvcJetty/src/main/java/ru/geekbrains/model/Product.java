package ru.geekbrains.model;

public class Product {

    private int id;
    private String title;
    private float cost;


    public Product() {
        this.id = 0;
        this.title = null;
        this.cost = 0f;
    }

    public Product(int id, String title, float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, float cost) {
        this(0, title, cost);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%d \"%s\" [%.2f]", id, title, cost);
    }
}
