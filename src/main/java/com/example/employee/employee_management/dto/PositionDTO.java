package com.example.employee.employee_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PositionDTO {

    private Long id;

    @NotNull(message = "Position title is required")
    @Size(min = 2, max = 10, message = "Position title must be between 2 and 10 characters")
    private String title;

    @NotNull(message = "Position responsibilities is required")
    @Size(min = 10, max = 500, message = "Position name must be between 10 and 500 characters")
    private String responsibilities;

    public PositionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
