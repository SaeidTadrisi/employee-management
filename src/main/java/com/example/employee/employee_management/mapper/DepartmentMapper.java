package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.model.Department;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department department){
        if (department == null){
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }

    public List<DepartmentDTO> toDTOList(List<Department> departments){
        if (departments == null){
            return Collections.emptyList();
        }
        return departments.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public Department toEntity(DepartmentDTO departmentDTO){
        if (departmentDTO == null){
            return null;
        }
        Department department = new Department();
        department.setName(departmentDTO.getName());
        return department;
    }
}
