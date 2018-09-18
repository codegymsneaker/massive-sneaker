package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ProductService {
    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

    Page<Product> findAll(Pageable pageable);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByBrand(Brand brand);

    Page<Product> findAllByNameContainingOrCode(String name, String code, Pageable pageable);

}