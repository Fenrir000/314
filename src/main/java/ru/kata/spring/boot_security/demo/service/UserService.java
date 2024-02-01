package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    void save(User user);


    List<User> findAll();

    Optional<User> findById(Long id);

    void updateUser(User user);

    void deleteById(Long id);

    Optional<User> findByEmail(String email);
}