package com.example.employee.employee_management.service.query;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentQueryService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public DepartmentQueryService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO findDepartmentById(Long id){
        return departmentMapper.toDTO(departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department", id)));
    }

    public List<DepartmentDTO> findAllDepartments(){
        return departmentMapper.toDTOList(departmentRepository.findAll());
    }
}
