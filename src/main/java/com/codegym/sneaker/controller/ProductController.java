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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

        ModelAndView modelAndView = new ModelAndView("manage/manage-product");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/products/create")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("manage/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "create product successfully");
        return modelAndView;
    }

    @PostMapping("/products/create")
    public String saveProduct(
            @ModelAttribute("product") Product product,
            RedirectAttributes redirectAttributes
    ) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "New create product successfully");
        return "redirect:/product";
    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView showEditProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("manage/edit");
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

    @RequestMapping(value = "/products/delete{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
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

//    @RequestMapping("/search")
//    public @ResponseBody List searchPost(@RequestParameter("term") String query) {
//
//        List<Object> retVal = getListOfObjectFromDbBasedOnQuery(query);
//
//        return retVal;
//    }
}
