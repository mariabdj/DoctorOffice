package com.DoctorOffice.DoctorOffice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.Medecin;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.RendezVous;
import com.DoctorOffice.DoctorOffice.repository.RendezVousRepository;

@Service
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final MedecinService medecinService;

    @Autowired
    public RendezVousService(RendezVousRepository rendezVousRepository, MedecinService medecinService) {
        this.rendezVousRepository = rendezVousRepository;
        this.medecinService = medecinService;
    }

    public void addAppointment(Date dateRen, String heure, String medecinId, Patient patient) {
        System.out.println("Adding appointment...");
        // Retrieve Medecin information using the medecinId
        Medecin medecin = medecinService.getMedecinById(medecinId); 
        if (medecin != null) {
            System.out.println("Medecin found: " + medecin.getNom() + " " + medecin.getPrenom());
            // Create a new appointment instance
            RendezVous rendezVous = new RendezVous(dateRen, heure, patient, medecin);
            // Save the appointment to the database
            rendezVousRepository.save(rendezVous);
            System.out.println("Appointment saved!");
        } else {
            System.out.println("Medecin not found!");
        }
    }
}
