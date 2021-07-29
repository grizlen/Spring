package ru.geekbrains.SpringMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.SpringMarket.model.dto.ProductDTO;
import ru.geekbrains.SpringMarket.services.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getAll(
            @RequestParam(required = false) Float min_price,
            @RequestParam(required = false) Float max_price,
            @RequestParam(required = false) String title,
//            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (min_price != null) {
            params.add("min_price", String.valueOf(min_price));
        }
        if (max_price != null) {
            params.add("max_price", String.valueOf(max_price));
        }
        if (title != null) {
            params.add("title", title);
        }
        return productService.findAll(params, page, 2);
    }

//    @GetMapping
//    public Page<ProductDTO> getAll(
//            @RequestParam MultiValueMap<String, String> params,
//            @RequestParam(name = "page", defaultValue = "0") Integer page
//    ){
//        return productService.findAll(params, page, 2);
//    }
//
    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productService.getById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product with Id: " + id + " not found.")
        );
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO add(@RequestBody ProductDTO product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ProductDTO edit(@RequestBody ProductDTO product){
        return productService.edit(product);
    }
}
