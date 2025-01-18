package com.example.employee.employee_management.service;

import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository
            , PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setPosition(updatedEmployee.getPosition());
                    employee.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee assignDepartment(Long employeeId, Long departmentId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));


        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee assignPosition(Long employeeId, Long positionId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found with id: " + positionId));

        employee.setPosition(position);

        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeByIdWithDepartmentAndPosition(Long id){
        return employeeRepository.findEmployeeByIdWithDepartmentAndPosition(id);
    }
}
