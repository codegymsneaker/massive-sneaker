package com.codegym.sneaker.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductForm {

    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String code;
    private int size;
    private MultipartFile image;
    private String imageUrl;

    private Brand brand;
    private List<Category> categories;

    public ProductForm() {
    }

    public ProductForm(String name, double price, int quantity, String code, int size, MultipartFile image, String imageUrl, Brand brand, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.code = code;
        this.size = size;
        this.image = image;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
