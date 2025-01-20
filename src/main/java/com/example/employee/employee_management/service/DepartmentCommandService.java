package com.example.employee.employee_management.service;

import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DepartmentCommandService {

    DepartmentRepository departmentRepository;

    public DepartmentCommandService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department){
        if (departmentRepository.existsByName(department.getName())){
            throw new DuplicateEntityException("Department with name '" + department.getName() + "' already exists");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment){
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new EntityNotFoundException("Department", id));
    }

    public void deleteDepartmentById(Long id){
        if (!departmentRepository.existsById(id)){
            throw new EntityNotFoundException("Department", id);
        }
        departmentRepository.deleteById(id);
    }
}
