package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/product_form")
    public String editProduct(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            id = 0;
        }
        Product product = productService.getProduct(id);
        if (product == null) {
            product = new Product("Новый товар", 0f);
        }
        model.addAttribute("product", product);
        return "product_form";
    }

    @PostMapping("/product_form")
    public String saveProduct(@ModelAttribute Product product) {
        if (product != null) {
            productService.saveProduct(product);
        }
        return "redirect:products";
    }

    @GetMapping("/product_delete")
    public String deleteProduct(@RequestParam(name = "id", required = false) Integer id) {
        if (id != null) {
            productService.deleteProduct(id);
        }
        return "redirect:products";
    }
}
