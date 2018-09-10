package com.codegym.sneaker.repository;

import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @Query("SELECT c.products from Category c WHERE  c.id = :id")
    Iterable<Product> findAllProductByCategory(@Param("id") long categoryId);
}
