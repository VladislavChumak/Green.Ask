package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/index";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/index";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с такими данными уже существует");
            return "/index";
        }
        return "redirect:/login";
    }
}
