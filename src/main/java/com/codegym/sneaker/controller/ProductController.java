package com.codegym.sneaker.controller;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.model.Product;
import com.codegym.sneaker.model.ProductForm;
import com.codegym.sneaker.service.BrandService;
import com.codegym.sneaker.service.CategoryService;
import com.codegym.sneaker.service.ProductService;
import com.codegym.sneaker.utils.StorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {
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

    @GetMapping("/product")
    public ModelAndView listProduct(
            @RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Product> products;
        if (s.isPresent()) {
            products = productService.findAllByName(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/products/create")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productForm", new ProductForm());
//        modelAndView.addObject("message", "create product successfully");
        return modelAndView;
    }

    @PostMapping("/products/create")
    public ModelAndView saveProduct(
            @ModelAttribute("productForm") ProductForm productForm,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        try {
            String randomCode = UUID.randomUUID().toString();
            String originFileName = productForm.getImage().getOriginalFilename();
            String randomName = randomCode + StorageUtils.getFileExtension(originFileName);
            productForm.getImage().transferTo(new File(StorageUtils.FEATURE_LOCATION + "/" + randomName));

            Product product = new Product();
            product.setCode(productForm.getCode());
            product.setName(productForm.getName());
            product.setSize(productForm.getSize());
            product.setQuantity(productForm.getQuantity());
            product.setImage(randomName);
            productService.save(product);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        productService.save(product);
//        redirectAttributes.addFlashAttribute("message", "New create product successfully");
//        return "redirect:/product";
        modelAndView.addObject("formPost", new ProductForm());
        modelAndView.addObject("message", "create blog successfully");
        return modelAndView;
    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView showEditProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/product/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit/product")
    public String updateProduct(
            @ModelAttribute("product") Product product,
            RedirectAttributes redirectAttributes
    ) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "update product successfully");
        return "redirect:/product";
    }

    @GetMapping("/products/delete/{id}")
    public ModelAndView showDeleteProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/product/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete/product")
    public String removeProduct(
            @ModelAttribute("product") Product product,
            RedirectAttributes redirectAttributes
    ) {
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("message", "remove product successfully");
        return "redirect:/product";
    }

    @GetMapping("/products/view/{id}")
    public ModelAndView showViewProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/view");
            modelAndView.addObject("products", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/product");
            return modelAndView;
        }
    }
}
