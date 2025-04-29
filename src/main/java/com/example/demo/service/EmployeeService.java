package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import jakarta.validation.Valid;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllDoctors() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getDoctorById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getDoctorsByStatus(String status) {
        return employeeRepository.findByStatus(status);
    }

    public List<Employee> getDoctorsByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Employee addDoctor(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateDoctorStatus(Integer employeeId, String status) {
        return employeeRepository.findById(employeeId)
                .map(doctor -> {
                    doctor.setStatus(status);
                    return employeeRepository.save(doctor);
                });
    }

    public Optional<Employee> updateDoctorDepartment(Integer employeeId, String department) {
        return employeeRepository.findById(employeeId)
                .map(doctor -> {
                    doctor.setDepartment(department);
                    return employeeRepository.save(doctor);
                });
    }
}