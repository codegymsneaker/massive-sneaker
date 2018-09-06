package com.codegym.sneaker.repository;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByBrand(Brand brand);

    Page<Product> findAllByName(String name, Pageable pageable);
}