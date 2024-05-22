package com.DoctorOffice.DoctorOffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.DoctorOffice.DoctorOffice.entity.Consultation;
import com.DoctorOffice.DoctorOffice.entity.DossierMedicale;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.service.ConsultationService;
import com.DoctorOffice.DoctorOffice.service.DossierMedicaleService;
import com.DoctorOffice.DoctorOffice.service.SessionCompteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicaleFileController {
    private final SessionCompteService sessionCompteService;
    private final ConsultationService consultationService;
    private final DossierMedicaleService dossierMedicaleService;

    @Autowired
    public MedicaleFileController(SessionCompteService sessionCompteService, ConsultationService consultationService, DossierMedicaleService dossierMedicaleService) {
        this.sessionCompteService = sessionCompteService;
        this.consultationService = consultationService;
        this.dossierMedicaleService = dossierMedicaleService;
    }

    @GetMapping("/redirectMedicalFile")
public String redirectMedicaleFile(HttpSession session, Model model) {
    System.out.println("WE ARRIVED 1");
    SessionCompte account = (SessionCompte) session.getAttribute("account");
    Patient patient;

    // Check if the user is authenticated through session or directly accessing patient data
    if (account != null && account.getUtilisateur() != null) {
        String username = account.getUtilisateur();
        String password = account.getMotDePasse();
        patient = sessionCompteService.findPatientByUsernameAndPassword(username, password);
        System.out.println("000name :" +patient.getNom()+" Prenom : " +patient.getPrenom());
    } else {
        // If the user is directly accessing patient data without authentication
        patient = (Patient) session.getAttribute("patient");
        System.out.println("111name :" +patient.getNom()+" Prenom : " +patient.getPrenom());
    }

    if (patient != null) {
        System.out.println("222name :" +patient.getNom()+" Prenom : " +patient.getPrenom());
        // Retrieve dossier and consultations for the patient
        DossierMedicale dossier = dossierMedicaleService.getDossierByPatient(patient);
        List<Consultation> consultations = consultationService.getConsultationsByPatient(patient);
        System.out.println("GroupeSanguin :" +dossier.getGroupeSanguin());
        // Add patient information, dossier, and consultations to the model
        String fullName = patient.getNom() + " " + patient.getPrenom();
        String bloodType = dossier.getGroupeSanguin(); // Check for null dossier
        model.addAttribute("fullName", fullName);
        model.addAttribute("bloodType", bloodType); // Changed "dossier" to "bloodType" for clarity
        model.addAttribute("consultations", consultations);
    }

    return "MedicalFile";
}

}
