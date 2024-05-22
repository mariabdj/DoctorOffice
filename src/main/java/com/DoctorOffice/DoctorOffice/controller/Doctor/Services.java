package com.DoctorOffice.DoctorOffice.controller.Doctor;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoctorOffice.DoctorOffice.entity.Consultation;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.repository.ConsultationRepository;
import com.DoctorOffice.DoctorOffice.service.ConsultationService;
import com.DoctorOffice.DoctorOffice.service.PatientService;
import com.DoctorOffice.DoctorOffice.service.RendezVousService;



@Controller
public class Services {
    
    private final PatientService patientService;
    private final ConsultationService consultationService;
    private final RendezVousService rendezVousService;


    @Autowired
    public Services(PatientService patientService, ConsultationService consultationService, RendezVousService rendezVousService) {
        this.patientService = patientService;
        this.consultationService = consultationService;
        this.rendezVousService = rendezVousService;
    }

    @GetMapping("/redirectServices")
    public String redirectServices() {
        return "Doctor/Services";
    }
    
    @PostMapping("/addPat")
    public String addPatient(@RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String gender, @RequestParam String maladies, @RequestParam String adresse, @RequestParam String numtel, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate, Model model) {
        System.out.println("hi");
        Patient patient = new Patient(nom, prenom, email, gender, maladies, adresse, numtel, birthdate);
        try {
            patientService.savePatient(patient);
            System.out.println("hi HI HHHHHHHHHHH");
            model.addAttribute("successMessage", "Patient ajouté avec succès");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'ajout du patient: " + e.getMessage());
        }
        return "Doctor/Services";
    }

    @PostMapping("/addCons")
    public String addConsultation(@RequestParam String diagnostic,
                                  @RequestParam String medicament,
                                  @RequestParam Long matPat,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateCons,
                                  Model model) {
        try {
            // Retrieve the patient by ID
            Patient patient = patientService.getPatientById(matPat);
            
            // Check if the patient exists
            if (patient != null) {
                // Create a new consultation object with the provided details
                Consultation consultation = new Consultation(diagnostic, dateCons, medicament, patient);
                
                // Save the consultation
                consultationService.saveConsultation(consultation);
                
                // Add a success message to the model
                model.addAttribute("successMessage", "Consultation added successfully");
            } else {
                // Add an error message if the patient is not found
                model.addAttribute("errorMessage", "Patient not found with the specified ID");
            }
        } catch (Exception e) {
            // Add an error message if an exception occurs
            model.addAttribute("errorMessage", "Error adding consultation: " + e.getMessage());
        }
        
        // Return the view name
        return "Doctor/Services";
    }
    
    @PostMapping("/addApp")
public String addAppointment(@RequestParam String heure, 
                             @RequestParam Long matPat, 
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRen, 
                             @RequestParam String medecin,
                             Model model) {
    try {
        // Retrieve the patient by ID
        Patient patient = patientService.getPatientById(matPat);
        
        // Check if the patient exists
        if (patient != null) {
            // Add the appointment
            rendezVousService.addAppointment(dateRen, heure, medecin, patient);
            
            // Add a success message to the model
            model.addAttribute("successMessage", "Appointment added successfully");
        } else {
            // Add an error message if the patient is not found
            model.addAttribute("errorMessage", "Patient not found with the specified ID");
        }
    } catch (Exception e) {
        // Add an error message if an exception occurs
        model.addAttribute("errorMessage", "Error adding appointment: " + e.getMessage());
    }
    return "Doctor/Services";
}

}
