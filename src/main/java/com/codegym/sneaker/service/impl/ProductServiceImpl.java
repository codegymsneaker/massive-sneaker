package com.codegym.sneaker.service.impl;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import com.codegym.sneaker.repository.CategoryRepository;
import com.codegym.sneaker.repository.ProductRepository;
import com.codegym.sneaker.service.ProductService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {

        List<Category> categories = new ArrayList<>();
        for (Category category : product.getCategories()) {
            categories.add(categoryRepository.findOne(category.getId()));
        }
        product.setCategories(categories);

        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        products.forEach(product -> Hibernate.initialize(product.getCategories()));
        return products;
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return categoryRepository.findAllProductByCategory(category.getId());
    }

    @Override
    public Iterable<Product> findAllByBrand(Brand brand) {
        return productRepository.findAllByBrand(brand);
    }

    @Override
    public Page<Product> findAllByName(String name, Pageable pageable) {
        return productRepository.findAllByName(name, pageable);
    }
}