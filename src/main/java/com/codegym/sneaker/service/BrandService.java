package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Brand;

public interface BrandService {
    Iterable<Brand> findAll();

    Brand findById(Long id);

    void save(Brand brand);

    void remove(Long id);

}