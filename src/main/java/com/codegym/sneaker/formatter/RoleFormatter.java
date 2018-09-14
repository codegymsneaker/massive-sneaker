package com.codegym.sneaker.formatter;

import com.codegym.sneaker.model.Role;
import com.codegym.sneaker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Locale;

@Component
public class RoleFormatter implements Formatter<Role> {

    private RoleService roleService;

    @Autowired
    public RoleFormatter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Long id = Long.parseLong(text);
        Role role = roleService.findById(id);
        return role;
    }

    @Override
    public String print(Role object, Locale locale) {
        return object.toString();
    }
}