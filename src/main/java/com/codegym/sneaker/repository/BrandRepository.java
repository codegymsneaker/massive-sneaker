package com.codegym.sneaker.repository;

import com.codegym.sneaker.model.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
}
