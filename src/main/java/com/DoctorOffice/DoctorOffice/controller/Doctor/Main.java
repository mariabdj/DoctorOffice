package com.DoctorOffice.DoctorOffice.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {
    private final String pass = "FGHET2004", doc="doctor";

    @GetMapping("/")
    public String home() {
        return "Doctor/Home";  // Points to src/main/resources/templates/doctor/home.html
    }

    @PostMapping("/done")
    public String signIn(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equalsIgnoreCase(doc) && password.equalsIgnoreCase(pass)) {
            // Redirect to the tabbar controller method
            return "redirect:/Doctor/tabbarD";
        } else {
            // Display error message if username doesn't exist or password is wrong
            model.addAttribute("errorMess", "Wrong username or password");
            return "Doctor/Home";
        }
    }
}
