package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    void testSaveAndFindById() {
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        employee = employeeRepository.save(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());

        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
        assertEquals(50000.0, found.get().getSalary());
        assertEquals("John@Doe.com", found.get().getEmail());
        assertEquals(employee.getId(), found.get().getId());
    }

    @Test
    void testExistsByEmail() {
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        employeeRepository.save(employee);

        boolean found = employeeRepository.existsByEmail("John@Doe.com");
        assertTrue(found);
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        employee = employeeRepository.save(employee);

        employeeRepository.delete(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());
        assertTrue(found.isEmpty());
    }

    @Test
    void testUpdateDepartment() {
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        employee = employeeRepository.save(employee);

        employee.setName("Sam Altman");
        employee = employeeRepository.save(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());
        assertTrue(found.isPresent());
        assertEquals("Sam Altman", found.get().getName());
    }
}
