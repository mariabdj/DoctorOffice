package com.DoctorOffice.DoctorOffice.repository;

import com.DoctorOffice.DoctorOffice.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}
