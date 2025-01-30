package com.example.employee.employee_management;

import com.example.employee.employee_management.model.Department;
import com.example.employee.employee_management.model.Employee;
import com.example.employee.employee_management.repository.DepartmentRepository;
import com.example.employee.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmployeeManagementSystemApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void assignDepartment_ShouldPersistData() throws Exception {
        Department department = departmentRepository.save(new Department("HR"));
        Employee employee = employeeRepository.save(new Employee("Alice", 20000.0, "Alice@test.com"));

        mockMvc.perform(patch("/api/employees/{employeeId}/assign-department/{departmentId}"
                        , employee.getId(), department.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value("HR"));

        Employee updatedEmployee = employeeRepository.findById(employee.getId()).orElseThrow();
        assertEquals("HR", updatedEmployee.getDepartment().getName());
    }
}
