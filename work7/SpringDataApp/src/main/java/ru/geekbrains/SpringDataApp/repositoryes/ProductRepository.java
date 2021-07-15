package ru.geekbrains.SpringDataApp.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringDataApp.entiries.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameLike(String name);

    List<Product> findByPriseGreaterThanEqual(Float max);

    List<Product> findByPriseBetween(Float min, Float max);
}

