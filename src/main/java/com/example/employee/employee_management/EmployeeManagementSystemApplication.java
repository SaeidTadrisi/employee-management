package com.example.employee.employee_management;

import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner cm (EmployeeService employeeService){

		return runner -> {
		};
}

/*	private static Employee extracted(EmployeeService employeeService) {
		Employee employee = new Employee("John Doe", 50000.0);
		return employeeService.saveEmployee(employee);
	}*/
}
