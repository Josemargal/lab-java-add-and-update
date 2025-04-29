package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @NotNull(message = "Employee ID cannot be null")
    private Integer employeeId;

    @Column(name = "department")
    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "status")
    @NotBlank(message = "Status cannot be blank")
    @Pattern(regexp = "ON|OFF|ON_CALL", message = "Status must be ON, OFF, or ON_CALL")
    private String status;

    // Constructors
    public Employee() {
    }

    public Employee(Integer employeeId, String department, String name, String status) {
        this.employeeId = employeeId;
        this.department = department;
        this.name = name;
        this.status = status;
    }

    // Getters and Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
