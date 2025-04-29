package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Patient p WHERE p.admittedBy.department = :department")
    List<Patient> findByAdmittingDoctorDepartment(@Param("department") String department);

    @Query("SELECT p FROM Patient p WHERE p.admittedBy.status = :status")
    List<Patient> findByAdmittingDoctorStatus(@Param("status") String status);
}
