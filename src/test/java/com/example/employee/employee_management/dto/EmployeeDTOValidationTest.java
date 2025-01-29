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

public class EmployeeDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");

        Set<ConstraintViolation<EmployeeDTO>> violation = validator.validate(dto);
        assertTrue(violation.isEmpty());
    }

    @Test
    void testNullNameEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(null);
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidNameSizeEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("A");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testNullEmailEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail(null);

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidEmailEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@.com");

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testNullSalaryEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(null);
        dto.setEmail("John@Doe.com");

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidSalaryEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(-50000.0);
        dto.setEmail("John@Doe.com");

        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

}
