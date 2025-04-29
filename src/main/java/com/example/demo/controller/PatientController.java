package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // 5. Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // 6. Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer patientId) {
        return patientService.getPatientById(patientId)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 7. Get patients by date of birth range
    @GetMapping("/by-dob")
    public ResponseEntity<List<Patient>> getPatientsByDateOfBirthRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Patient> patients = patientService.getPatientsByDateOfBirthRange(startDate, endDate);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // 8. Get patients by admitting doctor's department
    @GetMapping("/by-department/{department}")
    public ResponseEntity<List<Patient>> getPatientsByAdmittingDoctorDepartment(
            @PathVariable String department) {
        List<Patient> patients = patientService.getPatientsByAdmittingDoctorDepartment(department);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // 9. Get all patients with a doctor whose status is OFF
    @GetMapping("/with-doctor-off")
    public ResponseEntity<List<Patient>> getPatientsWithDoctorOffStatus() {
        List<Patient> patients = patientService.getPatientsWithDoctorOffStatus();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // 10. Add new patient
    @PostMapping
    public ResponseEntity<Patient> addNewPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    // 11. Update patient information
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatientInformation(
            @PathVariable("id") Integer patientId,
            @RequestBody Patient patientDetails) {
        return patientService.updatePatient(patientId, patientDetails)
                .map(updatedPatient -> new ResponseEntity<>(updatedPatient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}