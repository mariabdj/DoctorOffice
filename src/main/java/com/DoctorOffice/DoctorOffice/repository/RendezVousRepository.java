package com.DoctorOffice.DoctorOffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.Patient;
import com.DoctorOffice.DoctorOffice.entity.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByDateRen(Date dateRen);
     List<RendezVous> findByPatient(Patient patient);
     List<RendezVous> findByPatientAndDateRenBetween(Patient patient, Date startDate, Date endDate);
     List<RendezVous> findByDateRenBetween(Date startDate, Date endDate);
}
