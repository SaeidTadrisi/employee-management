package com.example.employee.employee_management;

import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.service.DepartmentService;
import com.example.employee.employee_management.service.EmployeeService;
import com.example.employee.employee_management.service.PositionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner cm (EmployeeService employeeService
			, DepartmentService departmentService
			, PositionService positionService){

		return runner -> {

//			createEmployee(employeeService);
//			createDepartment(departmentService);
//			createPosition(positionService);

//			employeeService.assignDepartment(1L,1L);
//			employeeService.assignPosition(1L,1L);

//			positionService.findPositionById(1L).ifPresent(position -> {
//				List<Employee> employees = position.getEmployees();
//				System.out.println(position);
//				System.out.println("================>");
//				System.out.println(employees);
//			});
//
//			departmentService.findDepartmentById(1L).ifPresent(department -> {
//				List<Employee> employees = department.getEmployees();
//				System.out.println(department);
//				System.out.println("================>");
//				System.out.println(employees);
//			});

//			Optional<Position> positionByIdWithEmployees = positionService.findPositionByIdWithEmployees(1L);
//			positionByIdWithEmployees.ifPresent(position -> {
//				List<Employee> employees = position.getEmployees();
//				System.out.println(employees);
//			});

			departmentService.findDepartmentByIdWithEmployees(1L).ifPresent(department -> {
				List<Employee> employees = department.getEmployees();
				System.out.println(department);
				System.out.println("==========>");
				System.out.println(employees);
			});
		};
}

	private Position createPosition(PositionService positionService) {
		return positionService.createPosition(new Position("Software Developer"
		, """
				Implement CRUD operations for employee records.
				Design and develop the backend using Java and frameworks like Spring Boot.
				Connect the application to the database using JPA/Hibernate.
				Write unit and integration tests for code reliability.
				"""));
	}


	private static Employee createEmployee(EmployeeService employeeService) {
		return employeeService.createEmployee(new Employee("Mate", 450000.0));
	}

	private Department createDepartment(DepartmentService departmentService) {
		return departmentService.createDepartment(new Department("HR"));
	}

}
