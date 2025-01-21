package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee){
        if (employee == null){
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPositionId(employee.getPosition() != null ? employee.getPosition().getId() : null);
        employeeDTO.setPositionTitle(employee.getPosition() != null ? employee.getPosition().getTitle() : null);
        employeeDTO.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null);
        employeeDTO.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null);
        return employeeDTO;
    }

    public List<EmployeeDTO> toDTOList(List<Employee> employees){
        if (employees == null){
            return null;
        }
        return employees.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public Employee toEntity(EmployeeDTO employeeDTO){
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }
}
