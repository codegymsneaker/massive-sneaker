package com.codegym.sneaker.service;

import com.codegym.sneaker.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Iterable<User> findUserByNameContaining(String name);

    Iterable<User> findUserByLastNameContaining(String lastName);

    User findUserByEmail(String email);

    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}