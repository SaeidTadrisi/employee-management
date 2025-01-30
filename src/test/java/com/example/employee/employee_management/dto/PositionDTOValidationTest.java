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

public class PositionDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidPositionDTO() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop Software");

        Set<ConstraintViolation<PositionDTO>> violation = validator.validate(dto);
        assertTrue(violation.isEmpty());
    }

    @Test
    void testNullTitlePositionDTO() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle(null);
        dto.setResponsibilities("Develop Software");

        Set<ConstraintViolation<PositionDTO>> violation = validator.validate(dto);
        assertFalse(violation.isEmpty());
    }

    @Test
    void testInvalidTitleSizePositionDTO() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("S");
        dto.setResponsibilities("Develop Software");

        Set<ConstraintViolation<PositionDTO>> violation = validator.validate(dto);
        assertFalse(violation.isEmpty());
    }

    @Test
    void testNullResponsibilitiesPositionDTO() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities(null);

        Set<ConstraintViolation<PositionDTO>> violation = validator.validate(dto);
        assertFalse(violation.isEmpty());
    }

    @Test
    void testInvalidResponsibilitiesSizePositionDTO() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop");

        Set<ConstraintViolation<PositionDTO>> violation = validator.validate(dto);
        assertFalse(violation.isEmpty());
    }
}
