package main;

public class Product {

    private final int id;
    private final String title;
    private final float cost;

    public Product(int id, String title, float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%d: \"%s\" %.2f", id, title, cost);
    }
}
