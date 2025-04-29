package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.validation.Valid;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer patientId) {
        return patientRepository.findById(patientId);
    }

    public List<Patient> getPatientsByDateOfBirthRange(LocalDate startDate, LocalDate endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    public List<Patient> getPatientsByAdmittingDoctorDepartment(String department) {
        return patientRepository.findByAdmittingDoctorDepartment(department);
    }

    public List<Patient> getPatientsWithDoctorOffStatus() {
        return patientRepository.findByAdmittingDoctorStatus("OFF");
    }

    public Patient addPatient(@Valid Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatient(Integer patientId, Patient patientDetails) {
        return patientRepository.findById(patientId)
                .map(existingPatient -> {
                    // Actualizar todos los campos que no sean nulos
                    if (patientDetails.getName() != null) {
                        existingPatient.setName(patientDetails.getName());
                    }
                    if (patientDetails.getDateOfBirth() != null) {
                        existingPatient.setDateOfBirth(patientDetails.getDateOfBirth());
                    }
                    if (patientDetails.getAdmittedBy() != null) {
                        existingPatient.setAdmittedBy(patientDetails.getAdmittedBy());
                    }
                    return patientRepository.save(existingPatient);
                });
    }
}