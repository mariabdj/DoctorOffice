package com.DoctorOffice.DoctorOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.Medecin;
import com.DoctorOffice.DoctorOffice.repository.MedecinRepository;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    public Medecin getMedecinById(String id) {
        return medecinRepository.findById(id).orElse(null);
    }
}
