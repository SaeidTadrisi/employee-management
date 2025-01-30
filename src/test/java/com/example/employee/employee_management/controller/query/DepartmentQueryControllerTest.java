package com.example.employee.employee_management.controller.query;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.service.query.DepartmentQueryService;
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

public class DepartmentQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DepartmentQueryService departmentQueryService;

    @InjectMocks
    private DepartmentQueryController departmentQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentQueryController).build();
    }
    @Test
    void getDepartmentById_ShouldReturnDepartment() throws Exception {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("HR");
        dto.setId(1L);

        when(departmentQueryService.findDepartmentById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    void getAllDepartments_ShouldReturnDepartments() throws Exception {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("HR");

        DepartmentDTO dto2 = new DepartmentDTO();
        dto2.setName("QA");

        when(departmentQueryService.findAllDepartments()).thenReturn(List.of(dto, dto2));

        mockMvc.perform(get("/api/departments/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("HR"))
                .andExpect(jsonPath("$[1].name").value("QA"));
    }
}
