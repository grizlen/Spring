package ru.geekbrains.SpringSecurityapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringSecurityapp.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
