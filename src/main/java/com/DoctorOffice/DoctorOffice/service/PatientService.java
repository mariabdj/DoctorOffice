package com.DoctorOffice.DoctorOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.repository.PatientRepository;
import com.DoctorOffice.DoctorOffice.repository.SessionCompteRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final SessionCompteRepository sessionCompteRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, SessionCompteRepository sessionCompteRepository) {
        this.patientRepository = patientRepository;
        this.sessionCompteRepository = sessionCompteRepository;
    }

    public boolean existsByUsername(String username) {
        return sessionCompteRepository.existsByUtilisateur(username);
    }

    public void savePatient(Patient patient, String username, String password) {
        // Save patient
        Patient savedPatient = patientRepository.save(patient);
        // Create session compte for the patient
        SessionCompte sessionCompte = new SessionCompte();
        sessionCompte.setUtilisateur(username);
        sessionCompte.setMotDePasse(password);
        sessionCompte.setPatient(savedPatient);
        // Save session compte
        sessionCompteRepository.save(sessionCompte);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }


}
