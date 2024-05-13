package com.DoctorOffice.DoctorOffice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "SessionCompte")
public class SessionCompte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numSesCom_seq")
    @SequenceGenerator(name = "numSesCom_seq", sequenceName = "numSesCom_seq", allocationSize = 1)
    @Column(name = "numSesCom")
    private Long numSesCom;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "motDePasse")
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "matPat_SesCom", referencedColumnName = "matPat")
    private Patient patient; // Many sessions belong to one patient


    public SessionCompte() {
    }

    
    public SessionCompte(String utilisateur, String motDePasse) {
        this.utilisateur = utilisateur;
        this.motDePasse = motDePasse;
    }

    public Long getNumSesCom() {
        return numSesCom;
    }

    public void setNumSesCom(Long numSesCom) {
        this.numSesCom = numSesCom;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
}
