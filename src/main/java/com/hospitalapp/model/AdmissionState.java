package com.hospitalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.Set;

@Entity
public class AdmissionState extends HospitalEntity {

    private static final long serialVersionUID = 1L;

    @Null
    private Instant exitingDate;

    @NotNull
    private Instant enteringDate;

    @NotNull
    private Boolean discharge;

    public Boolean getDischarge() {
        return this.discharge;
    }

    public void setDischarge(Boolean discharge) {
        this.discharge = discharge;
    }

    public Instant getEnteringDate() {
        return this.enteringDate;
    }

    public void setEnteringDate(Instant enteringDate){
        this.enteringDate = enteringDate;
    }

    public void setExitingDate(Instant exitingDate) {
        this.exitingDate = exitingDate;
    }

    public Instant getExitingDate(){
        return this.exitingDate;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId")
    private Patient patient;

    public AdmissionState() {
        super();
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }
}
