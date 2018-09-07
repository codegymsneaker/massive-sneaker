package com.codegym.sneaker.service.impl;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import com.codegym.sneaker.repository.ProductRepository;
import com.codegym.sneaker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllByBrand(Brand brand) {
        return productRepository.findAllByBrand(brand);
    }

    @Override
<<<<<<< HEAD:src/main/java/com/codegym/sneaker/service/ProductServiceImpl.java
    public Page<Product> findAllByNames(String name, Pageable pageable) {
=======
    public Page<Product> findAllByName(String name, Pageable pageable) {
>>>>>>> e56d4b163d1c28d36a7379077d679d70267d3bb0:src/main/java/com/codegym/sneaker/service/impl/ProductServiceImpl.java
        return productRepository.findAllByName(name, pageable);
    }
}