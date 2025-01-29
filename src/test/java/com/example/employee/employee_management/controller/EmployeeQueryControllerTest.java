package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.controller.query.EmployeeQueryController;
import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.service.query.EmployeeQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeQueryService employeeQueryService;

    @InjectMocks
    private EmployeeQueryController employeeQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeQueryController).build();
    }
    @Test
    void getEmployeeById_ShouldReturnEmployee() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");
        dto.setId(1L);

        when(employeeQueryService.findEmployeeById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void getAllEmployees_ShouldReturnEmployees() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");

        EmployeeDTO dto2 = new EmployeeDTO();
        dto2.setName("Mary Alta");
        dto2.setSalary(20000.0);
        dto2.setEmail("Mary@Alta.com");

        when(employeeQueryService.findAllEmployees()).thenReturn(List.of(dto, dto2));

        mockMvc.perform(get("/api/employees/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Mary Alta"));
    }
}
