package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserDAO;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder encoder;
    private final UserDAO userDao;

    public UserServiceImpl(PasswordEncoder encoder, UserDAO userDao) {
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void save(User user) {
        Optional<User> us = userDao.findByEmail(user.getEmail());
        if (us.isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if(user.getPassword().equals(findById(user.getId()).get().getPassword())){
            userDao.updateUser(user);
        } else{
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.updateUser(user);}
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));


    }
}