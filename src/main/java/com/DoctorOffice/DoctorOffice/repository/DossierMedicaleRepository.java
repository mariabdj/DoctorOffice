package com.DoctorOffice.DoctorOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.DossierMedicale;


@Repository
public interface DossierMedicaleRepository extends JpaRepository<DossierMedicale, Long> {
}
