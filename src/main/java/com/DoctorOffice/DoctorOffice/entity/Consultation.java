package com.DoctorOffice.DoctorOffice.entity;

import java.util.Date;

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
@Table(name = "Consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numCons_seq")
    @SequenceGenerator(name = "numCons_seq", sequenceName = "numCons_seq", allocationSize = 1)
    @Column(name = "numCons")
    private Long numCons;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "dateCons")
    private Date dateCons;

    @Column(name = "medicament")
    private String medicament;

    @ManyToOne
    @JoinColumn(name = "matPat_Dos", referencedColumnName = "matPat")
    private Patient patient; // Many consultations belong to one patient


    public Consultation(Long numCons, String diagnostic, Date dateCons, String medicament, Patient patient) {
        this.numCons = numCons;
        this.diagnostic = diagnostic;
        this.dateCons = dateCons;
        this.medicament = medicament;
        this.patient = patient;
    }

    public Consultation() {
    }

    public Long getNumCons() {
        return numCons;
    }

    public void setNumCons(Long numCons) {
        this.numCons = numCons;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Date getDateCons() {
        return dateCons;
    }

    public void setDateCons(Date dateCons) {
        this.dateCons = dateCons;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
