package ru.geekbrains.main;

import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Product product;
        String title;
        float cost;
        long id;

        try (ProductService productService = new ProductService()) {
            System.out.println("Create:");
            product = productService.create(title = "new product", cost = 10f);
            System.out.println(product != null ? "Create: " + product : String.format("Failure to create: Product \"%s\" %.2f", title, cost));

            System.out.println("All products:");
            productService.findAll().forEach(System.out::println);

            System.out.println("Find:");
            product = productService.findById(id = 6);
            System.out.println(product != null ? product : String.format("Product with id = %d not found", id));

            System.out.println("Update:");
            product = productService.setTitle(id = 1, title = "Product 1 renamed");
            System.out.println(product != null ? product : String.format("Product with id = %d not found", id));
            product = productService.setCost(id = 1, cost = 12.5f);
            System.out.println(product != null ? product : String.format("Product with id = %d not found", id));

            System.out.println("Delete:");
            System.out.println(productService.delete(id = 5) ?
                    String.format("Product with id = %d deleted", id) :
                    String.format("Product with id = %d not found", id));
        }
    }
}
