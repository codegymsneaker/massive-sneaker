package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Role;

public interface RoleService {

    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void remove(Long id);
}