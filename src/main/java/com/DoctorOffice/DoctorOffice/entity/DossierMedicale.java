package com.DoctorOffice.DoctorOffice.entity;

import javax.persistence.*;

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
    private Patient patient;

    @OneToOne(mappedBy = "consultation")
    private Consultation consultation;

    public DossierMedicale(Long numDos, String groupeSanguin, Patient patient, Consultation consultation) {
        this.numDos = numDos;
        this.groupeSanguin = groupeSanguin;
        this.patient = patient;
        this.consultation = consultation;
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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    
}
