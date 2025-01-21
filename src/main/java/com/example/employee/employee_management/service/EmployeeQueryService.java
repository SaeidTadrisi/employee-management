package com.example.employee.employee_management.service;
import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import com.example.employee.employee_management.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeQueryService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;
    EmployeeMapper employeeMapper;

    public EmployeeQueryService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository
            , PositionRepository positionRepository
            , EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO findEmployeeById(Long id){
        return employeeMapper.toDTO(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id)));
    }

    public List<EmployeeDTO> findAllEmployees(){
        return employeeMapper.toDTOList(employeeRepository.findAll());
    }
}
