package com.example.employee.employee_management.service.query;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.EmployeeMapper;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeQueryServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    EmployeeQueryService employeeQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findEmployeeById_ShouldReturnEmployeeDTO() {
        Long employeeId = 1L;
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setSalary(50000.0);
        employeeDTO.setEmail("John@Doe.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeQueryService.findEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(50000.0, result.getSalary());
        assertEquals("John@Doe.com", result.getEmail());
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeMapper, times(1)).toDTO(employee);
    }

    @Test
    void findEmployeeById_ShouldThrowEntityNotFoundException() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeQueryService.findEmployeeById(employeeId));
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeMapper, never()).toDTO(any(Employee.class));
    }

    @Test
    void findAllEmployees_ShouldReturnListOfEmployees() {
        Employee employee1 = new Employee("John Doe", 50000.0, "John@Doe.com");
        Employee employee2 = new Employee("Mary Alta", 20000.0, "Mary@Alta.com");

        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setName("John Doe");
        employeeDTO1.setSalary(50000.0);
        employeeDTO1.setEmail("John@Doe.com");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setName("Mary Alta");
        employeeDTO2.setSalary(20000.0);
        employeeDTO2.setEmail("Mary@Alta.com");

        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));
        when(employeeMapper.toDTOList(List.of(employee1, employee2))).thenReturn(List.of(employeeDTO1, employeeDTO2));

        List<EmployeeDTO> result = employeeQueryService.findAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Mary Alta", result.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).toDTOList(anyList());
    }

    @Test
    void findAllDepartments_ShouldReturnEmptyList_WhenNoDepartmentsExist() {

        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        when(employeeMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<EmployeeDTO> result = employeeQueryService.findAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).toDTOList(anyList());
    }
}