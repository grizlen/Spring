package ru.geekbrains.SpringMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringMarket.model.Category;
import ru.geekbrains.SpringMarket.model.Product;

@Repository
public interface CategryRepository extends JpaRepository<Category, Long>{
    Category findByTitle(String category);
}

