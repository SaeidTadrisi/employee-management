package com.example.employee.employee_management.service.query;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.DepartmentMapper;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentQueryServiceTest {

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    DepartmentMapper departmentMapper;

    @InjectMocks
    DepartmentQueryService departmentQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findDepartmentById_ShouldReturnDepartmentDTO() {
        Long departmentId = 1L;
        Department department = new Department("IT");
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("IT");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(departmentMapper.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO result = departmentQueryService.findDepartmentById(departmentId);

        assertNotNull(result);
        assertEquals("IT", result.getName());
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentMapper, times(1)).toDTO(department);
    }

    @Test
    void findDepartmentById_ShouldThrowEntityNotFoundException() {
        Long departmentId = 1L;

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> departmentQueryService.findDepartmentById(departmentId));
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentMapper, never()).toDTO(any());
    }

    @Test
    void findAllDepartments_ShouldReturnListOfDepartmentDTOs() {
        Department hr = new Department("HR");
        Department finance = new Department("Finance");

        DepartmentDTO hrDTO = new DepartmentDTO();
        hrDTO.setName("HR");
        DepartmentDTO financeDTO = new DepartmentDTO();
        financeDTO.setName("Finance");

        when(departmentRepository.findAll()).thenReturn(List.of(hr, finance));
        when(departmentMapper.toDTOList(List.of(hr, finance))).thenReturn(List.of(hrDTO, financeDTO));

        List<DepartmentDTO> result = departmentQueryService.findAllDepartments();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("HR", result.get(0).getName());
        assertEquals("Finance", result.get(1).getName());
        verify(departmentRepository, times(1)).findAll();
        verify(departmentMapper, times(1)).toDTOList(anyList());
    }

    @Test
    void findAllDepartments_ShouldReturnEmptyList_WhenNoDepartmentsExist() {

        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());
        when(departmentMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<DepartmentDTO> result = departmentQueryService.findAllDepartments();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(departmentRepository, times(1)).findAll();
        verify(departmentMapper, times(1)).toDTOList(anyList());
    }
}