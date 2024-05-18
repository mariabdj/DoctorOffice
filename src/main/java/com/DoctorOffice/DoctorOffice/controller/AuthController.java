package com.DoctorOffice.DoctorOffice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.service.PatientService;
import com.DoctorOffice.DoctorOffice.service.SessionCompteService;


import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final SessionCompteService sessionCompteService;
    private final PatientService patientService;
    private String signUpUsername; // Store username temporarily
    private String signUpPassword; // Store password temporarily
    private int n = 0;

    @Autowired
    public AuthController(SessionCompteService sessionCompteService, PatientService patientService) {
        this.sessionCompteService = sessionCompteService;
        this.patientService = patientService;
    }

    @PostMapping("/signin")
public String signIn(String username, String password, Model model, HttpSession session) {
    if (sessionCompteService.isValidUser(username, password)) {
        if(n==0 && username!=null){
            SessionCompte account = new SessionCompte(username, password);
            session.setAttribute("account", account); // Add account to session
            System.out.println("Username :" +account.getUtilisateur()+" Mot de passe : " +account.getMotDePasse());
            n=1;
        }
        // Debug output
        System.out.println("Session account in signIn: " + session.getAttribute("account")); 
        // Redirect to the tabbar controller method
        return "redirect:/tabbar";
    } else {
        // Display error message if username doesn't exist or password is wrong
        model.addAttribute("errorMess", "Wrong username or password");
        return "index";
    }
}


    @PostMapping("/signup")
    public String signUp(String username, String password, String verifyPassword, Model model) {
        // Check if the username already exists
        if (patientService.existsByUsername(username)) {
            model.addAttribute("errorMess", "Username taken");
            return "index";
        } else {
            // Check if passwords match
            if (password!=null && !password.equals(verifyPassword)) {
                model.addAttribute("errorMess", "Password mismatch");
                return "index";
            } else {

                if(password!=null && username!=null){
                    // Store username and password temporarily
                   signUpUsername = username;
                   signUpPassword = password;
                }
                // Redirect to registration.html after successful signup
                return "redirect:/template/registration.html";
            }
        }
    }

    @GetMapping("/template/registration.html")
    public String showRegistrationForm(Model model) {
        // Add any necessary attributes to the model
        return "registration"; // Assuming "registration" is the name of your registration HTML template
    }


    @PostMapping("/register")
    public String register(@RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String gender, @RequestParam String maladies, @RequestParam String adresse, @RequestParam String numtel, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate, Model model, HttpSession session) {
        // Create a Patient object with the provided information
        Patient patient = new Patient(nom, prenom, email, gender, maladies, adresse, numtel, birthdate);
        if(nom!=null && prenom!=null){
            if(n==0 && patient!=null){
                session.setAttribute("patient", patient); // Add patient to session
                n=1;
            }
        }
        // Save the patient
        patientService.savePatient(patient, signUpUsername, signUpPassword);
        // Redirect to tabbar.html after successful registration
        return "redirect:/template/tabbar.html";
    }
    

@GetMapping("/tabbar")
    public String showTabBar() {
        // Add any necessary attributes to the model
        return "tabbar"; // Assuming "tabbar" is the name of your tabbar HTML template
    }

}
