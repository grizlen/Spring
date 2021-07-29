package ru.geekbrains.SpringMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.SpringMarket.model.Cart;
import ru.geekbrains.SpringMarket.model.dto.ProductDTO;
import ru.geekbrains.SpringMarket.repositories.CartRepository;
import ru.geekbrains.SpringMarket.services.ProductService;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    @Resource(name = "getCartRepository")
    private CartRepository cartRepository;
    private final ProductService productService;

    @GetMapping("/all")
    public List<Cart> getProducts() {
        return cartRepository.getProducts();
    }

    @GetMapping("/add/{id}")
    public ProductDTO addProduct(@PathVariable Long id, @RequestParam(name = "count", defaultValue = "1") Integer count) {
        ProductDTO productDTO = productService.getById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found"));
        cartRepository.addProduct(id, count);
        return productDTO;
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cartRepository.deleteProduct(id);
    }
}
