package com.DoctorOffice.DoctorOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.SessionCompte;
import com.DoctorOffice.DoctorOffice.repository.SessionCompteRepository;

@Service
public class SessionCompteService {

    private final SessionCompteRepository sessionCompteRepository;

    @Autowired
    public SessionCompteService(SessionCompteRepository sessionCompteRepository) {
        this.sessionCompteRepository = sessionCompteRepository;
    }

    // Method to check if a user with the given username exists in the database
    public boolean existsByUsername(String username) {
        return sessionCompteRepository.existsByUtilisateur(username);
    }

    // Method to check if a user with the given username and password exists in the database
    public boolean isValidUser(String username, String password) {
        SessionCompte sessionCompte = sessionCompteRepository.findByUtilisateur(username);
        if (sessionCompte != null) {
            String storedPassword = sessionCompte.getMotDePasse();
            if (storedPassword != null && storedPassword.equals(password)) {
                return true; // Username exists and password matches
            }
        }
        return false; // Either username doesn't exist or password doesn't match
    }

    

}
