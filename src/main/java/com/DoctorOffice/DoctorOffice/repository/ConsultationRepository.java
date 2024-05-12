package com.DoctorOffice.DoctorOffice.repository;

import com.DoctorOffice.DoctorOffice.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
