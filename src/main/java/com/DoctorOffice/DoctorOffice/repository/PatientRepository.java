package com.DoctorOffice.DoctorOffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContainingIgnoreCase(String nom);
}
