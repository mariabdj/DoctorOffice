package com.DoctorOffice.DoctorOffice.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Medecin")
public class Medecin {
    @Id
    @Column(name = "medStat")
    private String medStat;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "status")
    private String status; // Add status field

    public Medecin(String medStat, String nom, String prenom, String status) {
        this.medStat = medStat;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
    }

    

    public Medecin() {
    }



    public String getMedStat() {
        return medStat;
    }

    public void setMedStat(String medStat) {
        this.medStat = medStat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Constructors, getters, and setters


    
}
