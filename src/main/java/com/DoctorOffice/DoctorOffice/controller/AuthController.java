package com.DoctorOffice.DoctorOffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.DoctorOffice.DoctorOffice.service.SessionCompteService;

@Controller
public class AuthController {

    private final SessionCompteService sessionCompteService;

    @Autowired
    public AuthController(SessionCompteService sessionCompteService) {
        this.sessionCompteService = sessionCompteService;
    }

    @PostMapping("/signin")
    public String signIn(String username, String password, Model model) {
        if (sessionCompteService.isValidUser(username, password)) {
            // Redirect to tabbar.html if username and password are correct
            return "redirect:/template/tabbar.html";
        } else {
            // Display error message if username doesn't exist or password is wrong
            model.addAttribute("errorMess", "Wrong username or password");
            return "index";
        }
    }

    @PostMapping("/signup")
    public String signUp(String username, String password, String verifyPassword, Model model) {
        // Check if the username already exists
        if (sessionCompteService.existsByUsername(username)) {
            model.addAttribute("errorMess", "Username taken");
            return "index";
        } else {
            // Check if passwords match
            if (!password.equals(verifyPassword)) {
                model.addAttribute("errorMess", "Password mismatch");
                return "index";
            } else {
                // Redirect to registration.html with username and password
                return "redirect:/template/registration.html";
            }
        }
    }
}
