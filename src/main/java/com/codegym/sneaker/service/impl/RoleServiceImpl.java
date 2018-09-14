package com.codegym.sneaker.service.impl;

import com.codegym.sneaker.model.Role;
import com.codegym.sneaker.repository.RoleRepository;
import com.codegym.sneaker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository rolesRepository;

    @Override
    public Iterable<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return rolesRepository.findOne(id);
    }

    @Override
    public void save(Role role) {
        rolesRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        rolesRepository.delete(id);
    }
}