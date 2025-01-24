package com.example.employee.employee_management.service.command;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.EmployeeMapper;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeCommandServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    PositionRepository positionRepository;

    @InjectMocks
    private EmployeeCommandService employeeCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployee_ShouldCreateEmployee() {
        Employee employee = new Employee("John Doe",30000.0, "John@Doe.com");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setSalary(30000.0);
        employeeDTO.setEmail("John@Doe.com");

        when(employeeMapper.toEntity(employeeDTO)).thenReturn(employee);
        when(employeeRepository.existsByEmail("John@Doe.com")).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeCommandService.createEmployee(employeeDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("John@Doe.com", result.getEmail());
        assertEquals(30000.0, result.getSalary());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void createEmployee_ShouldThrowDuplicateEntityException() {
        String employeeEmail = "John@Doe.com";

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setSalary(30000.0);
        employeeDTO.setEmail("John@Doe.com");

        when(employeeRepository.existsByEmail(employeeEmail)).thenReturn(true);
        when(employeeMapper.toEntity(employeeDTO))
                .thenReturn(new Employee("John Doe",30000.0, "John@Doe.com"));
        assertThrows(DuplicateEntityException.class, () -> employeeCommandService.createEmployee(employeeDTO));

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void updateEmployee_ShouldUpdateEmployee() {
        Long employeeId = 1L;
        Employee existingEmployee = new Employee("John Doe",30000.0, "John@Doe.com");
        existingEmployee.setId(employeeId);

        EmployeeDTO updatedEmployee = new EmployeeDTO();
        updatedEmployee.setName("John Doe");
        updatedEmployee.setSalary(45000.0);
        updatedEmployee.setEmail("John.Doe@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeMapper.toEntity(updatedEmployee))
                .thenReturn(new Employee("John Doe", 45000.0, "John.Doe@example.com"));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(updatedEmployee);

        EmployeeDTO result = employeeCommandService.updateEmployee(employeeId, updatedEmployee);

        assertNotNull(result);
        assertEquals(45000.0, result.getSalary());
        assertEquals("John.Doe@example.com", result.getEmail());
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void updateEmployee_ShouldThrowEntityNotFoundException() {
        Long employeeId = 1L;

        EmployeeDTO updatedEmployee = new EmployeeDTO();
        updatedEmployee.setName("John Doe");
        updatedEmployee.setSalary(45000.0);
        updatedEmployee.setEmail("John.Doe@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class
                , () -> employeeCommandService.updateEmployee(employeeId, updatedEmployee));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void deleteEmployeeById_ShouldDeleteEmployee() {
        Long employeeId = 1L;

        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        employeeCommandService.deleteEmployeeById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void deleteEmployeeById_ShouldThrowEntityNotFoundException() {
        Long employeeId = 1L;

        when(employeeRepository.existsById(employeeId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> employeeCommandService.deleteEmployeeById(employeeId));
        verify(employeeRepository, never()).deleteById(employeeId);
    }

    @Test
    void assignDepartment_ShouldAssignDepartment() {
        Long employeeId = 1L;
        Long departmentId = 10L;

        Employee existingEmployee = new Employee("John Doe",30000.0, "John@Doe.com");
        existingEmployee.setId(employeeId);

        Department existingDepartment = new Department("HR");
        existingDepartment.setId(departmentId);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeCommandService.assignDepartment(employeeId, departmentId);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(existingDepartment, existingEmployee.getDepartment());
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void assignDepartment_ShouldThrowEntityNotFoundException_WhenEmployeeNotFound() {
        Long employeeId = 1L;
        Long departmentId = 10L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class
                , () -> employeeCommandService.assignDepartment(employeeId, departmentId));
        verify(departmentRepository, never()).findById(any());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void assignDepartment_ShouldThrowEntityNotFoundException_WhenDepartmentNotFound() {
        Long employeeId = 1L;
        Long departmentId = 10L;
        Employee existingEmployee = new Employee("John Doe",30000.0, "John@Doe.com");
        existingEmployee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class
                , () -> employeeCommandService.assignDepartment(employeeId, departmentId));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void assignPosition_ShouldAssignPosition() {
        Long employeeId = 1L;
        Long positionId = 10L;

        Employee existingEmployee = new Employee("John Doe",30000.0, "John@Doe.com");
        existingEmployee.setId(employeeId);

        Position existingPosition = new Position("Developer", "Develop software");
        existingPosition.setId(positionId);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(positionRepository.findById(positionId)).thenReturn(Optional.of(existingPosition));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeCommandService.assignPosition(employeeId, positionId);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(employeeRepository, times(1)).save(existingEmployee);
        assertEquals(existingPosition, existingEmployee.getPosition());
    }

    @Test
    void assignPosition_ShouldThrowEntityNotFoundException_WhenEmployeeNotFound() {
        Long employeeId = 1L;
        Long positionId = 10L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class
                , () -> employeeCommandService.assignPosition(employeeId, positionId));
        verify(employeeRepository, never()).save(any());
        verify(positionRepository, never()).findById(any());
    }

    @Test
    void assignPosition_ShouldThrowEntityNotFoundException_WhenPositionNotFound() {
        Long employeeId = 1L;
        Long positionId = 10L;
        Employee existingEmployee = new Employee("John Doe",30000.0, "John@Doe.com");
        existingEmployee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(positionRepository.findById(positionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class
                , () -> employeeCommandService.assignPosition(employeeId, positionId));
        verify(employeeRepository, never()).save(any());
    }
}