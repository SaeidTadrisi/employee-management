package com.example.employee.employee_management;

import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.repository.EmployeeRepository;
import com.example.employee.employee_management.service.EmployeeService;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
		System.out.println(employeeService.createEmployee(new Employee("Mike", 350000.0)));
		System.out.println(employeeService.findEmployeeById(1L));
	}

}
