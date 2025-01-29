package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void testSaveAndFindById() {
        Department department = new Department("HR");
        department = departmentRepository.save(department);

        Optional<Department> found = departmentRepository.findById(department.getId());

        assertTrue(found.isPresent());
        assertEquals("HR", found.get().getName());
        assertEquals(department.getId(), found.get().getId());
    }

    @Test
    void testExistsByName() {
        Department department = new Department("HR");
        departmentRepository.save(department);

        boolean found = departmentRepository.existsByName("HR");

        assertTrue(found);
    }

    @Test
    void testDelete() {
        Department department = new Department("HR");
        department = departmentRepository.save(department);

        departmentRepository.delete(department);

        Optional<Department> found = departmentRepository.findById(department.getId());
        assertTrue(found.isEmpty());
    }

    @Test
    void testUpdateDepartment() {
        Department department = new Department("HR");
        department = departmentRepository.save(department);

        department.setName("HR Updated");
        department = departmentRepository.save(department);

        Optional<Department> found = departmentRepository.findById(department.getId());
        assertTrue(found.isPresent());
        assertEquals("HR Updated", found.get().getName());
    }
}
