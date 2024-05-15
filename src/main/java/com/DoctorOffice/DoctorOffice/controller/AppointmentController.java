package com.DoctorOffice.DoctorOffice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.service.RendezVousService;
import com.DoctorOffice.DoctorOffice.service.SessionCompteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppointmentController {

    private final SessionCompteService sessionCompteService;
    private final RendezVousService rendezVousService;


    @Autowired
    public AppointmentController(SessionCompteService sessionCompteService, RendezVousService rendezVousService) {
        this.sessionCompteService = sessionCompteService;
        this.rendezVousService = rendezVousService;
    }

    @GetMapping("/templates/TakeAppointement.html")
public String TakeAppointment(Model model, HttpSession session) {
    // Add any necessary attributes to the model
    return "TakeAppointement"; // This will load TakeAppointement.html into tabbar.html
}



@PostMapping("/take")
public String register(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                       @RequestParam String hour,
                       @RequestParam String doctor,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
    // Retrieve account and patient from session
    SessionCompte account = (SessionCompte) session.getAttribute("account");
    Patient patient = (Patient) session.getAttribute("patient");
    System.out.println("Session account: " + account); // Debug output
    System.out.println("Session patient: " + patient); // Debug output


    // Perform null checks and add appointment
    if (patient != null) {
        rendezVousService.addAppointment(date, hour, doctor, patient);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment taken successfully!");
    } else {
        if (account != null && account.getUtilisateur() != null) {
            String username = account.getUtilisateur();
            String password = account.getMotDePasse();
            patient = sessionCompteService.findPatientByUsernameAndPassword(username, password);
            if (patient != null) {
                rendezVousService.addAppointment(date, hour, doctor, patient);
                redirectAttributes.addFlashAttribute("successMessage", "Appointment taken successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to take appointment. Patient information is missing.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to take appointment. Session information is missing.");
        }
    }

    return "tabbar";
}

}
