package com.DoctorOffice.DoctorOffice.entity;

import java.util.Date;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "numDos_Dos", referencedColumnName = "numDos")
    private DossierMedicale dossierMedicale; // Each consultation belongs to a dossierMedicale

    public Consultation(Long numCons, String diagnostic, Date dateCons, String medicament, Patient patient, DossierMedicale dossierMedicale) {
        this.numCons = numCons;
        this.diagnostic = diagnostic;
        this.dateCons = dateCons;
        this.medicament = medicament;
        this.patient = patient;
        this.dossierMedicale = dossierMedicale;
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

    public DossierMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossierMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }
}
