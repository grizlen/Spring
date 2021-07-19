package ru.geekbrains.SpringMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping
    public Page<Product> getAll(
            @RequestParam(name = "min_price", required = false) Float minPrice,
            @RequestParam(name = "max_price", required = false) Float maxPrice,
            @RequestParam(name = "page", defaultValue = "0") int page
    ){
        return productService.getAll(minPrice, maxPrice, page);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/like")
    public Page<Product> like(@RequestParam String name) {
        return productService.like(name, 0);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public Product edit(@RequestBody Product product){
        return productService.edit(product);
    }
}
