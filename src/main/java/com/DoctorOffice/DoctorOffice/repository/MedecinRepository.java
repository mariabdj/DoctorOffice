package com.DoctorOffice.DoctorOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.Medecin;


@Repository
public interface MedecinRepository extends JpaRepository<Medecin, String> {
}
