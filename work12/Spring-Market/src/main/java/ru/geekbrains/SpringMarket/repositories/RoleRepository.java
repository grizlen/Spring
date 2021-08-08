package ru.geekbrains.SpringMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.SpringMarket.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}
