package com.example.employee.employee_management.service;

import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;

import java.util.Optional;

public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Optional<Department> findDepartmentById(Long id){
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(Long id, Department updatedDepartment){
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    return departmentRepository.save(department);
                })
                .orElse(null);
    }

    public void deleteDepartmentById(Long id){
        departmentRepository.deleteById(id);
    }
}