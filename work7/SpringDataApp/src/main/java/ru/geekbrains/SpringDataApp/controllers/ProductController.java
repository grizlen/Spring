package ru.geekbrains.SpringDataApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringDataApp.entiries.Product;
import ru.geekbrains.SpringDataApp.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAll(
            @RequestParam(name = "min", required = false) Float min,
            @RequestParam(name = "max", required = false) Float max
    ){
        return productService.getAll(min, max);
    }

    @GetMapping("/{id}")
    public Product getbyId(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/add")
    public Product add(@RequestParam("name") String name, @RequestParam("prise") Float prise) {
        return productService.add(name, prise);
    }

    @GetMapping("/delete")
//    @DeleteMapping("/delete")
    public List<Product> delete(@RequestParam Long id) {
        return productService.delete(id);
    }

    @GetMapping("/like")
    public List<Product> like(@RequestParam String name) {
        return productService.like(name);
    }
}
