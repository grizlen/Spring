package ru.geekbrains.SpringMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.SpringMarket.model.Cart;
import ru.geekbrains.SpringMarket.model.dto.ProductDTO;
import ru.geekbrains.SpringMarket.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return (List<ProductDTO>) cart.getProducts()
                .stream()
                .map(id -> productService.getById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found")));
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody Long productId) {
        return productService.getById(cart.AddProduct(productId)).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cart.deleteProduct(id);
    }
}
