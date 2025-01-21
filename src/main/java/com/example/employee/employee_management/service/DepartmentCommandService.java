package com.example.employee.employee_management.service;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import com.example.employee.employee_management.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DepartmentCommandService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public DepartmentCommandService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO){

        Department departmentMapperEntity = departmentMapper.toEntity(departmentDTO);

        if (departmentRepository.existsByName(departmentMapperEntity.getName())){
            throw new DuplicateEntityException("Department with name '" + departmentMapperEntity.getName() + "' already exists");
        }

        return departmentMapper.toDTO(departmentRepository.save(departmentMapperEntity));
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO updatedDepartmentDTO){

        Department departmentEntity = departmentMapper.toEntity(updatedDepartmentDTO);

        Department savedDepartment = departmentRepository.findById(id)
                .map(department -> {
                    department.setName(departmentEntity.getName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new EntityNotFoundException("Department", id));

        return departmentMapper.toDTO(savedDepartment);
    }

    public void deleteDepartmentById(Long id){
        if (!departmentRepository.existsById(id)){
            throw new EntityNotFoundException("Department", id);
        }
        departmentRepository.deleteById(id);
    }
}
