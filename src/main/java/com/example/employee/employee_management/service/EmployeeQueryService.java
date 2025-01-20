package com.example.employee.employee_management.service;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeQueryService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;

    public EmployeeQueryService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository
            , PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Employee findEmployeeByIdWithDepartmentAndPosition(Long id){
        return employeeRepository.findEmployeeByIdWithDepartmentAndPosition(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }
}
