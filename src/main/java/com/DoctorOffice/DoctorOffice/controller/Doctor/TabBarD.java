package com.DoctorOffice.DoctorOffice.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TabBarD{

    @GetMapping("/Doctor/tabbarD")
    public String redirect(Model model) {
        // You can load data or do any processing here if needed
        return "Doctor/tabbarD"; 
    }

}