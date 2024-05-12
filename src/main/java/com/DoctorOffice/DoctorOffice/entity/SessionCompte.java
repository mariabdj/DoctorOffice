package com.DoctorOffice.DoctorOffice.entity;

import javax.persistence.*;

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
    private Patient patient;

    public SessionCompte(Long numSesCom, String utilisateur, String motDePasse, Patient patient) {
        this.numSesCom = numSesCom;
        this.utilisateur = utilisateur;
        this.motDePasse = motDePasse;
        this.patient = patient;
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