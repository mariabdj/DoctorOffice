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
@Table(name = "RendezVous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numRen_seq")
    @SequenceGenerator(name = "numRen_seq", sequenceName = "numRen_seq", allocationSize = 1)
    @Column(name = "numRen")
    private Long numRen;

    @Column(name = "dateRen")
    private Date dateRen;

    @Column(name = "heure")
    private String heure;

    @ManyToOne
    @JoinColumn(name = "matPat_Ren", referencedColumnName = "matPat")
    private Patient patient; // Many appointments belong to one patient

    @ManyToOne
    @JoinColumn(name = "medStat_Ren", referencedColumnName = "medStat")
    private Medecin medecin; // Many appointments belong to one doctor

    public RendezVous() {
    }

    public RendezVous(Date dateRen, String heure, Patient patient, Medecin medecin) {
        this.dateRen = dateRen;
        this.heure = heure;
        this.patient = patient;
        this.medecin = medecin;
    }

    public Long getNumRen() {
        return numRen;
    }

    public void setNumRen(Long numRen) {
        this.numRen = numRen;
    }

    public Date getDateRen() {
        return dateRen;
    }

    public void setDateRen(Date dateRen) {
        this.dateRen = dateRen;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    
    
}
