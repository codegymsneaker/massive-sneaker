package com.codegym.sneaker.service;

import com.codegym.sneaker.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    Iterable<Brand> findAll();

    Brand findById(Long id);

    void save(Brand brand);

    void remove(Long id);
}