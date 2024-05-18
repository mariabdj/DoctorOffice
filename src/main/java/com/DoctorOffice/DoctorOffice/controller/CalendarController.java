package com.DoctorOffice.DoctorOffice.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.RendezVous;
import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.service.RendezVousService;
import  com.DoctorOffice.DoctorOffice.service.SessionCompteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarController {

    private final RendezVousService rendezVousService;
    private final SessionCompteService sessionCompteService;
    Long pat;

    @Autowired
    public CalendarController(RendezVousService rendezVousService, SessionCompteService sessionCompteService) {
        this.rendezVousService = rendezVousService;
        this.sessionCompteService = sessionCompteService;
    }

    @GetMapping("/redirectCalendar")
public String calendar(Model model, HttpSession session) {
    System.out.println("WE ARRIVED 1");
    // Retrieve patient from session
    Patient patient;
    SessionCompte account = (SessionCompte) session.getAttribute("account");
    System.out.println("Username: " + account.getUtilisateur() + " Mot de passe: " + account.getMotDePasse());
    if (account.getUtilisateur() != null) {
        patient = sessionCompteService.findPatientByUsernameAndPassword(account.getUtilisateur(), account.getMotDePasse());
    } else {
        patient = (Patient) session.getAttribute("patient");
    }

    System.out.println("Name: " + patient.getNom() + " Prenom: " + patient.getPrenom());

    // Get appointments for the patient by date
    Map<Date, List<RendezVous>> appointmentsByPatientAndDate = rendezVousService.getAppointmentsByPatientAndDate(patient);

    // Print out appointments and their hours
    for (Map.Entry<Date, List<RendezVous>> entry : appointmentsByPatientAndDate.entrySet()) {
        Date date = entry.getKey();
        List<RendezVous> appointments = entry.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Appointments for " + dateFormat.format(date) + ":");
        for (RendezVous appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getNumRen() + ", Hour: " + appointment.getHeure());
        }
    }

    System.out.println("Patient id : " + patient.getMatPat());
    pat = patient.getMatPat();

    // Add appointments and patientId to the model
    model.addAttribute("appointmentsByPatientAndDate", appointmentsByPatientAndDate);
   // Store patient ID in the session
    session.setAttribute("patientId", patient.getMatPat());
    return "Calendar";
}


@GetMapping("/getAppointmentsByMonth")
@ResponseBody
public Map<String, List<RendezVous>> getAppointmentsByMonth(@RequestParam Long patientId, @RequestParam int year, @RequestParam int month) {
    System.out.println("Received parameters: patientId=" + patientId + ", year=" + year + ", month=" + month);
    Map<String, List<RendezVous>> appointments = rendezVousService.getAppointmentsByPatientAndMonth(patientId, year, month);
    System.out.println("Appointments: " + appointments);
    return appointments;
}

@GetMapping("/getPatientIdFromSession")
@ResponseBody
public Long getPatientIdFromSession(HttpSession session) {
    return (Long) session.getAttribute("patientId");
}



}