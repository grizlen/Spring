package ru.geekbrains.SpringMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringMarket.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByTitle(String category);
}

