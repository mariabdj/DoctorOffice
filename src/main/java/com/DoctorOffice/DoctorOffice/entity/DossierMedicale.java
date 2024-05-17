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
@Table(name = "DossierMedicale")
public class DossierMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numDos_seq")
    @SequenceGenerator(name = "numDos_seq", sequenceName = "numDos_seq", allocationSize = 1)
    @Column(name = "numDos")
    private Long numDos;

    @Column(name = "groupeSanguin")
    private String groupeSanguin;

    @ManyToOne
    @JoinColumn(name = "matPat_Dos", referencedColumnName = "matPat")
    private Patient patient; // Many medical files belong to one patient

    public DossierMedicale(Long numDos, String groupeSanguin, Patient patient) {
        this.numDos = numDos;
        this.groupeSanguin = groupeSanguin;
        this.patient = patient;
    }

    public DossierMedicale() {
    }

    public Long getNumDos() {
        return numDos;
    }

    public void setNumDos(Long numDos) {
        this.numDos = numDos;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
