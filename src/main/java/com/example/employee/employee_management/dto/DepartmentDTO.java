package com.example.employee.employee_management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {

    private Long id;

    @NotNull(message = "Department Name is required")
    @Size(min = 3, max = 100, message = "Department name must be between 3 and 100 characters")
    private String name;

    public DepartmentDTO() {
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
}
