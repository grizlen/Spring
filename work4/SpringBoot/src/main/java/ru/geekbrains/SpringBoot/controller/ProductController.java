package ru.geekbrains.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.SpringBoot.model.Product;
import ru.geekbrains.SpringBoot.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    private List<Product> allProducts;

    @GetMapping("/products")
    public String allProducts(Model model) {
        allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "products";
    }

    @GetMapping("/product_form")
    public String editProduct(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            id = 0;
        }
//        не стал включать исключение так-как если
//        product == null тогда открывается форма для нового товара,
//        иначе редактирование существующего.

//        Integer finalId = id;
//        Product product = productService.getProduct(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + finalId + "not found"));
        Product product = productService.getProduct(id).orElse(new Product("Новый товар", 0f));
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
