package com.DoctorOffice.DoctorOffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DoctorOffice.DoctorOffice.entity.SessionCompte;

@Repository
public interface SessionCompteRepository extends JpaRepository<SessionCompte, Long> {
    
    // Using JPQL query to find SessionCompte by utilisateur
    @Query("SELECT sc FROM SessionCompte sc WHERE sc.utilisateur = :utilisateur")
    SessionCompte findByUtilisateur(@Param("utilisateur") String utilisateur);
    
    // Using JPQL query to check if SessionCompte exists by utilisateur
    @Query("SELECT CASE WHEN COUNT(sc) > 0 THEN TRUE ELSE FALSE END FROM SessionCompte sc WHERE sc.utilisateur = :utilisateur")
    boolean existsByUtilisateur(@Param("utilisateur") String utilisateur);
}
