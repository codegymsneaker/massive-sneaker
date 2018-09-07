package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);

    Page<Category> findAll(Pageable pageable);
}