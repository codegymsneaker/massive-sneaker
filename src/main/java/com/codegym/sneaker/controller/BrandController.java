package com.codegym.sneaker.controller;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.model.Category;
import com.codegym.sneaker.service.BrandService;
import com.codegym.sneaker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public ModelAndView listBrand() {
        Iterable<Brand> brands = brandService.findAll();
        ModelAndView modelAndView = new ModelAndView("/brand/list");
        modelAndView.addObject("brands", brands);
        return modelAndView;
    }

    @GetMapping("/create-brand")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/brand/create");
        modelAndView.addObject("brand", new Brand());
        return modelAndView;
    }

    @PostMapping("/create-brand")
    public ModelAndView saveBrand(@ModelAttribute("brand") Brand brand) {
        brandService.save(brand);
        ModelAndView modelAndView = new ModelAndView("/brand/create");
        modelAndView.addObject("brand", new Brand());
        modelAndView.addObject("message","Create new brand successful");
        return modelAndView;
    }

    @GetMapping("/edit-brand/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Brand brand = brandService.findById(id);
        if (brand != null) {
            ModelAndView modelAndView = new ModelAndView("/brand/edit");
            modelAndView.addObject("brand", brand);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-brand")
    public ModelAndView updateBrand(@ModelAttribute("brand") Brand brand) {
        brandService.save(brand);
        ModelAndView modelAndView = new ModelAndView("/brand/edit");
        modelAndView.addObject("brand", brand);
        modelAndView.addObject("message", "Brand updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-brand/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Brand brand = brandService.findById(id);
        if (brand != null) {
            ModelAndView modelAndView = new ModelAndView("/brand/delete");
            modelAndView.addObject("brand", brand);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-brand")
    public String deleteBrand(@ModelAttribute("brand") Brand brand) {
        brandService.remove(brand.getId());
        return "redirect:brands";
    }

    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }
}
