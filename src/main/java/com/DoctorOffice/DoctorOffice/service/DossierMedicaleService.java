package com.DoctorOffice.DoctorOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorOffice.DoctorOffice.entity.DossierMedicale;
import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.repository.DossierMedicaleRepository;

@Service
public class DossierMedicaleService {

    private final DossierMedicaleRepository dossierMedicaleRepository;

    @Autowired
    public DossierMedicaleService(DossierMedicaleRepository dossierMedicaleRepository) {
        this.dossierMedicaleRepository = dossierMedicaleRepository;
    }

    public DossierMedicale getDossierByPatient(Patient patient) {
        return dossierMedicaleRepository.findByPatient(patient);
    }
}
