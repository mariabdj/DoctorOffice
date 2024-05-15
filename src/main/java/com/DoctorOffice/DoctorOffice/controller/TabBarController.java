package com.DoctorOffice.DoctorOffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TabBarController {

    @GetMapping("/template/tabbar.html")
    public String redirect(Model model) {
        // You can load data or do any processing here if needed
        return "tabbar"; // This will load TakeAppointment.html into tabbar.html
    }

    @GetMapping("/redirectTakeAppointment")
    public String redirectTakeAppointment(Model model) {
        // You can load data or do any processing here if needed
        return "TakeAppointement"; // This will load TakeAppointment.html into tabbar.html
    }
}