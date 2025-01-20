package com.example.employee.employee_management.service;

import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeCommandService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;

    public EmployeeCommandService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository
            , PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public Employee createEmployee(Employee employee){
        if (employeeRepository.existsByEmail(employee.getEmail())){
            throw new DuplicateEntityException("Employee with Email '" + employee.getEmail() + "' already exists");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setPosition(updatedEmployee.getPosition());
                    employee.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }

    public Employee assignDepartment(Long employeeId, Long departmentId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));


        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department", departmentId));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee assignPosition(Long employeeId, Long positionId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new EntityNotFoundException("Position", positionId));

        employee.setPosition(position);

        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id){
        if (!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }

}
