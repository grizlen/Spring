package ru.geekbrains.SpringMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.SpringMarket.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
