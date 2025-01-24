package com.example.employee.employee_management.service.command;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.DepartmentMapper;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentCommandServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentCommandService departmentCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDepartment_ShouldCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("HR");
        Department department = new Department("HR");

        when(departmentMapper.toEntity(departmentDTO)).thenReturn(department);
        when(departmentRepository.existsByName("HR")).thenReturn(false);
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        when(departmentMapper.toDTO(any(Department.class))).thenReturn(departmentDTO);

        DepartmentDTO result = departmentCommandService.createDepartment(departmentDTO);

        assertNotNull(result);
        assertEquals("HR", result.getName());
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void createDepartment_ShouldThrowDuplicateEntityException() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("HR");
        Department department = new Department("HR");

        when(departmentRepository.existsByName("HR")).thenReturn(true);
        when(departmentMapper.toEntity(departmentDTO)).thenReturn(department);

        assertThrows(DuplicateEntityException.class, () -> departmentCommandService.createDepartment(departmentDTO));
        verify(departmentRepository, never()).save(any());
    }

    @Test
    void updateDepartment_ShouldUpdateDepartment() {
        Long departmentId = 1L;
        Department existingDepartment = new Department("HR");
        existingDepartment.setId(departmentId);

        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO();
        updatedDepartmentDTO.setName("QA");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(departmentMapper.toEntity(updatedDepartmentDTO)).thenReturn(new Department("QA"));
        when(departmentRepository.save(any(Department.class))).thenReturn(existingDepartment);
        when(departmentMapper.toDTO(any(Department.class))).thenReturn(updatedDepartmentDTO);

        DepartmentDTO result = departmentCommandService.updateDepartment(departmentId, updatedDepartmentDTO);

        assertNotNull(result);
        assertEquals("QA", result.getName());
        verify(departmentRepository, times(1)).save(existingDepartment);
    }

    @Test
    void updateDepartment_ShouldThrowEntityNotFoundException() {
        Long departmentId = 1L;

        DepartmentDTO updatesDepartmentDTO = new DepartmentDTO();
        updatesDepartmentDTO.setName("QA");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                ()-> departmentCommandService.updateDepartment(departmentId, updatesDepartmentDTO));
        verify(departmentRepository, never()).save(any());
    }

    @Test
    void deleteDepartmentById_ShouldDeleteDepartment() {
        Long departmentId = 1L;

        when(departmentRepository.existsById(departmentId)).thenReturn(true);
        departmentCommandService.deleteDepartmentById(departmentId);

        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    void deleteDepartmentById_ShouldThrowEntityNotFoundException() {
        Long departmentId = 1L;

        when(departmentRepository.existsById(departmentId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> departmentCommandService.deleteDepartmentById(departmentId));
        verify(departmentRepository, never()).deleteById(departmentId);
    }

}