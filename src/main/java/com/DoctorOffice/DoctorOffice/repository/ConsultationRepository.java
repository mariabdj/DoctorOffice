package com.DoctorOffice.DoctorOffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.Consultation;
import com.DoctorOffice.DoctorOffice.entity.Patient;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
        List<Consultation> findByPatient(Patient patient);
}
