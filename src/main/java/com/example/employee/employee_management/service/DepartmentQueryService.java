package com.example.employee.employee_management.service;

import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DepartmentQueryService {

    DepartmentRepository departmentRepository;

    public DepartmentQueryService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department findDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department", id));
    }

    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Department findDepartmentByIdWithEmployees(Long id){
        return departmentRepository.findDepartmentByIdWithEmployees(id)
                .orElseThrow(() -> new EntityNotFoundException("Department", id));
    }
}
