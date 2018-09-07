package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByBrand(Brand brand);

    Page<Product> findAllByName(String name, Pageable pageable);

}