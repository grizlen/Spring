package ru.geekbrains.SpringMarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import ru.geekbrains.SpringMarket.model.Cart;

@Configuration
public class CartBean {
    @Bean
    @SessionScope
    public Cart getCart() {
        return new Cart();
    }
}
