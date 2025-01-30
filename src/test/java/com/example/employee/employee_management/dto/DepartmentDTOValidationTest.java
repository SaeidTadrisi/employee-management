package com.example.employee.employee_management.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartmentDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDepartmentDTO() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("HR");

        Set<ConstraintViolation<DepartmentDTO>> violation = validator.validate(departmentDTO);
        assertTrue(violation.isEmpty());
    }

    @Test
    void testNullNameDepartmentDTO() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(null);

        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidNameSizeDepartmentDTO() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("A");

        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}

