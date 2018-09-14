package com.codegym.sneaker.service.impl;

import com.codegym.sneaker.model.Role;
import com.codegym.sneaker.model.User;
import com.codegym.sneaker.repository.RoleRepository;
import com.codegym.sneaker.repository.UserRepository;
import com.codegym.sneaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<User> findUserByNameContaining(String firstName) {
        return userRepository.findUserByFirstNameContaining(firstName);
    }

    @Override
    public Iterable<User> findUserByLastNameContaining(String lastName) {
        return userRepository.findUserByLastNameContaining(lastName);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        Role userRole = roleRepository.findByRole("USER");
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }
}