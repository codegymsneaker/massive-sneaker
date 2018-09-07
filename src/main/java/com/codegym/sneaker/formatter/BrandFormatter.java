package com.codegym.sneaker.formatter;

import com.codegym.sneaker.model.Brand;
import com.codegym.sneaker.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class BrandFormatter implements Formatter<Brand> {
    private BrandService brandService;

    @Autowired
    public BrandFormatter(BrandService brandService) {
        this.brandService =  brandService;
    }

    @Override
    public Brand parse(String text, Locale locale) throws ParseException {
        return brandService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Brand object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
