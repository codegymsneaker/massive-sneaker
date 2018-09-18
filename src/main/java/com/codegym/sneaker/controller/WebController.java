package com.codegym.sneaker.controller;


import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import com.codegym.sneaker.service.BrandService;
import com.codegym.sneaker.service.CategoryService;
import com.codegym.sneaker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class WebController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("brands")
    public Iterable<Brand> brands() {
        return brandService.findAll();
    }

    @GetMapping("/")
    public ModelAndView listHome(
            Pageable pageable
    ) {
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
