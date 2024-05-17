package com.DoctorOffice.DoctorOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.Consultation;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.repository.ConsultationRepository;

import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public List<Consultation> getConsultationsByPatient(Patient patient) {
        return consultationRepository.findByPatient(patient);
    }
}
