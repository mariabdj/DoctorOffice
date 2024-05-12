package com.DoctorOffice.repository;

import com.DoctorOffice.entity.SessionCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCompteRepository extends JpaRepository<SessionCompte, Long> {
}
