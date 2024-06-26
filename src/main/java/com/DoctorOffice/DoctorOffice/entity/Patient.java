package com.DoctorOffice.DoctorOffice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "Patient")
public class Patient {
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matPat_seq")
    @SequenceGenerator(name = "matPat_seq", sequenceName = "matPat_seq", allocationSize = 1)
    @Column(name = "matPat", length = 10) // Adjust length to match the database column
    private Long matPat;


    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "maladies")
    private String maladies;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numtel")
    private String numtel;

    @Column(name = "birthdate")
    private Date birthdate;

    

    public Patient() {
    }

    public Patient(String nom, String prenom, String email, String gender, String maladies, String adresse, String numtel, Date birthdate) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.gender = gender;
        this.maladies = maladies;
        this.adresse = adresse;
        this.numtel = numtel;
        this.birthdate = birthdate;
    }
    

    public Long getMatPat() {
        return matPat;
    }

    public void setMatPat(Long matPat) {
        this.matPat = matPat;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaladies() {
        return maladies;
    }

    public void setMaladies(String maladies) {
        this.maladies = maladies;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    
}
