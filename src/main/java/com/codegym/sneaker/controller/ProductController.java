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
import java.util.*;

@Controller
@RequestMapping("/admin")
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

    @GetMapping("/products")
    public ModelAndView listProduct(
            @RequestParam("s") Optional<String> s,
            Pageable pageable
    ) {
        Page<Product> products;
        if (s.isPresent()) {
            products = productService.findAllByName(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/manage/manage-product");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("/manage/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(
            @ModelAttribute("productForm") ProductForm productForm
    ) {
        ModelAndView modelAndView = new ModelAndView("/manage/create");
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
            product.setBrand(productForm.getBrand());
            product.setPrice(productForm.getPrice());
            product.setCategories(productForm.getCategories());
            product.setImage(randomName);

            productService.save(product);
            product.setCode("SNK" + product.getId());
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("productForm", new ProductForm());
        modelAndView.addObject("message", "New product has been created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        ProductForm productForm = new ProductForm();

        productForm.setId(product.getId());
        productForm.setCode(product.getCode());
        productForm.setName(product.getName());
        productForm.setSize(product.getSize());
        productForm.setQuantity(product.getQuantity());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImage());
        productForm.setBrand(product.getBrand());
        productForm.setCategories(product.getCategories());

        ModelAndView modelAndView;
        if (product != null) {
            modelAndView = new ModelAndView("/manage/edit");
            modelAndView.addObject("productForm", productForm);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("/manage/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateProduct(
            @ModelAttribute("productForm") ProductForm productForm,
            @PathVariable("id") Long id
    ) {
        ModelAndView modelAndView = new ModelAndView("/manage/edit");
        Product product = productService.findById(id);

        if (!productForm.getImage().isEmpty()) {
            StorageUtils.removeFeature(product.getImage());
            String randomCode = UUID.randomUUID().toString();
            String originFileName = productForm.getImage().getOriginalFilename();
            String randomName = randomCode + StorageUtils.getFileExtension(originFileName);
            try {
                productForm.getImage().transferTo(new File(StorageUtils.FEATURE_LOCATION + "/" + randomName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImage(randomName);
            productForm.setImageUrl(randomName);
        }
        product.setCode(productForm.getCode());
        product.setName(productForm.getName());
        product.setSize(productForm.getSize());
        product.setQuantity(productForm.getQuantity());
        product.setBrand(productForm.getBrand());
        product.setPrice(productForm.getPrice());
        product.setCategories(productForm.getCategories());

        productService.save(product);
        product.setCode("SNK" + product.getId());
        productService.save(product);
        modelAndView.addObject("productForm", productForm);
        modelAndView.addObject("message", "This product has been up to date successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        ModelAndView modelAndView;
        if (product != null) {
            modelAndView = new ModelAndView("/manage/delete");
            modelAndView.addObject("product", product);
        } else {
            modelAndView = new ModelAndView("/product/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String removeProduct(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Product has been deleted successfully");
            return "redirect:/admin/products";
        } else {
            return "/product/error-404";
        }
    }


    @GetMapping("/view/{id}")
    public ModelAndView showViewProductForm(
            @PathVariable("id") Long id
    ) {
        Product product = productService.findById(id);
        ModelAndView modelAndView;
        if (product != null) {
            modelAndView = new ModelAndView("/manage/view");
            modelAndView.addObject("products", product);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("redirect:/admin/products");
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
