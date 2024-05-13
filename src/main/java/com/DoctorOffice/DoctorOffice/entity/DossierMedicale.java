package com.DoctorOffice.DoctorOffice.entity;

import java.util.List;
import jakarta.persistence.*;

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

    @OneToMany(mappedBy = "dossierMedicale")
    private List<Consultation> consultations; // A medical file can have multiple consultations

    public DossierMedicale(Long numDos, String groupeSanguin, Patient patient, List<Consultation> consultations) {
        this.numDos = numDos;
        this.groupeSanguin = groupeSanguin;
        this.patient = patient;
        this.consultations = consultations;
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

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

}

