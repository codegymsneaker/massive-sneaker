package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Iterable<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

    Page<Product> findAll(Pageable pageable);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByBrand(Brand brand);

    Page<Product> findAllByName(String s, Pageable pageable);
}