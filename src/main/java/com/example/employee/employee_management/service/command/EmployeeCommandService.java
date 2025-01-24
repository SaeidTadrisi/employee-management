package com.example.employee.employee_management.service.command;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import com.example.employee.employee_management.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeCommandService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;
    EmployeeMapper employeeMapper;

    public EmployeeCommandService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository
            , PositionRepository positionRepository
            , EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){

        Employee employeeMapperEntity = employeeMapper.toEntity(employeeDTO);
        if (employeeRepository.existsByEmail(employeeMapperEntity.getEmail())){
            throw new DuplicateEntityException("Employee with Email '" + employeeMapperEntity.getEmail() + "' already exists");
        }

        return employeeMapper.toDTO(employeeRepository.save(employeeMapperEntity));
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO){
        Employee employeeEntity = employeeMapper.toEntity(updatedEmployeeDTO);

        Employee savedEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeEntity.getName());
                    employee.setEmail(employeeEntity.getEmail());
                    employee.setDepartment(employeeEntity.getDepartment());
                    employee.setPosition(employeeEntity.getPosition());
                    employee.setSalary(employeeEntity.getSalary());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new EntityNotFoundException("Employee", id));

        return employeeMapper.toDTO(savedEmployee);
    }

    public EmployeeDTO assignDepartment(Long employeeId, Long departmentId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department", departmentId));

        employee.setDepartment(department);

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO assignPosition(Long employeeId, Long positionId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new EntityNotFoundException("Position", positionId));

        employee.setPosition(position);

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    public void deleteEmployeeById(Long id){
        if (!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }

}
