package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}