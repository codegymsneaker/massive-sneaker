package com.codegym.sneaker.controller;

import com.codegym.sneaker.model.Role;
import com.codegym.sneaker.model.User;
import com.codegym.sneaker.service.RoleService;
import com.codegym.sneaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.spi.FileSystemProvider;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private String getPrincipal() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        return email;
    }

    @ModelAttribute("roles")
    public Iterable<Role> listRoles() {
        return roleService.findAll();
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/signup";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/success");
        modelAndView.addObject("user", user);
        modelAndView.addObject("msg", "Thank you for signing up");
        return modelAndView;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "/manage/manage-product";
    }

    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return "/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "/access_denied";
    }

}