package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    @NotNull(message = "Patient ID cannot be null")
    private Integer patientId;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "admitted_by", referencedColumnName = "employee_id")
    @NotNull(message = "Admitting doctor cannot be null")
    private Employee admittedBy;

    // Constructors
    public Patient() {
    }

    public Patient(Integer patientId, String name, LocalDate dateOfBirth, Employee admittedBy) {
        this.patientId = patientId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.admittedBy = admittedBy;
    }

    // Getters and Setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Employee getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(Employee admittedBy) {
        this.admittedBy = admittedBy;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admittedBy=" + admittedBy +
                '}';
    }
}
