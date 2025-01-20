package com.example.employee.employee_management.service;

import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findDepartmentById(Long id){
        return departmentRepository.findById(id);
    }

    @Transactional
    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, Department updatedDepartment){
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Transactional
    public void deleteDepartmentById(Long id){
        departmentRepository.deleteById(id);
    }

    @Transactional
    public Optional<Department> findDepartmentByIdWithEmployees(Long id){
        return departmentRepository.findDepartmentByIdWithEmployees(id);
    }
}