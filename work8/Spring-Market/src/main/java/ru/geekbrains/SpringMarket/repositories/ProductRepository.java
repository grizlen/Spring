package ru.geekbrains.SpringMarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringMarket.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByPriceGreaterThanEqual(Pageable pageable, Float min);

    Page<Product> findByPriceBetween(Pageable pageable, Float min, Float max);

    Page<Product> findAllByNameLike(Pageable pageable, String name);
}

