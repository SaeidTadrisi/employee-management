package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeMapperTest {

    EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    void toDTO_ShouldMapEmployeeToDTO() {
        Employee employee = new Employee("John Doe", 50000.0, "John@Doe.com");
        employee.setId(1L);

        EmployeeDTO dto = employeeMapper.toDTO(employee);

        assertNotNull(dto);
        assertEquals("John Doe", dto.getName());
        assertEquals(50000.0, dto.getSalary());
        assertEquals("John@Doe.com", dto.getEmail());
        assertEquals(1L , dto.getId());
    }

    @Test
    void toEntity_ShouldMapDTOToEmployee() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");

        Employee employee = employeeMapper.toEntity(dto);

        assertNotNull(employee);
        assertEquals("John Doe", employee.getName());
        assertEquals(50000.0, employee.getSalary());
        assertEquals("John@Doe.com", employee.getEmail());
    }

    @Test
    void toDTOList_ShouldMapListOfEmployeesToDTOs() {
        Employee employee1 = new Employee("John Doe", 50000.0, "John@Doe.com");
        Employee employee2 = new Employee("Mary Alta", 20000.0, "Mary@Alta.com");

        List<Employee> employees = List.of(employee1, employee2);
        List<EmployeeDTO> dtoList = employeeMapper.toDTOList(employees);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals("John Doe",  dtoList.get(0).getName());
        assertEquals(50000.0,  dtoList.get(0).getSalary());
        assertEquals("John@Doe.com",  dtoList.get(0).getEmail());
        assertEquals("Mary Alta", dtoList.get(1).getName());
        assertEquals(20000.0, dtoList.get(1).getSalary());
        assertEquals("Mary@Alta.com", dtoList.get(1).getEmail());
    }

    @Test
    void toDTOList_ShouldReturnEmptyListWhenInputIsNull() {
        List<EmployeeDTO> dtoList = employeeMapper.toDTOList(null);

        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
