package ru.geekbrains.SpringMarket.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import ru.geekbrains.SpringMarket.repositories.CartRepository;

@Configuration
public class CartBean {
    @Bean
    @SessionScope
    public CartRepository getCartRepository() {
        return new CartRepository();
    }
}
