package com.DoctorOffice.DoctorOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.Consultation;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
