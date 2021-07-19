package ru.geekbrains.SpringMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.model.Product;
import ru.geekbrains.SpringMarket.repositories.specifications.ProductSpecifications;
import ru.geekbrains.SpringMarket.services.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping
    public Page<Product> getAll(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ){
        return productService.findAll(ProductSpecifications.build(params), page, 2);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
