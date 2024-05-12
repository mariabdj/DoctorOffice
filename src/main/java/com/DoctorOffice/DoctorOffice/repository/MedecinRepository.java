package com.DoctorOffice.DoctorOffice.repository;

import com.DoctorOffice.DoctorOffice.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, String> {
}
