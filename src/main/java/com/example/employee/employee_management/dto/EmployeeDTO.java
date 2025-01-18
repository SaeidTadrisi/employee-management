package com.example.employee.employee_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.security.PrivateKey;

public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Employee Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be a positive value")
    private Double salary;

    @NotNull(message = "Position ID is required")
    private Long PositionId;

    @NotNull(message = "Position ID is required")
    private Long DepartmentId;

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getPositionId() {
        return PositionId;
    }

    public void setPositionId(Long positionId) {
        PositionId = positionId;
    }

    public Long getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Long departmentId) {
        DepartmentId = departmentId;
    }
}
