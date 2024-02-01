package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void updateUser(User user);

    void deleteById(Long id);

    Optional<User> findByEmail(String email);
}
