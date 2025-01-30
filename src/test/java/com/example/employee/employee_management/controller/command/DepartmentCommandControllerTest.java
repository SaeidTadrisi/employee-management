package com.example.employee.employee_management.controller.command;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.service.command.DepartmentCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepartmentCommandControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private DepartmentCommandService departmentCommandService;

    @InjectMocks
    private DepartmentCommandController departmentCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentCommandController).build();
    }
    @Test
    void createDepartment_ShouldReturnCreatedDepartment() throws Exception {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("HR");
        dto.setId(1L);

        when(departmentCommandService.createDepartment(any(DepartmentDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/departments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    void updateDepartment_ShouldReturnUpdatedDepartment() throws Exception {
        Long departmentId = 1L;
        DepartmentDTO updatedDto = new DepartmentDTO();
        updatedDto.setName("Updated HR");
        updatedDto.setId(departmentId);

        when(departmentCommandService.updateDepartment(eq(1L), any(DepartmentDTO.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/departments/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentId))
                .andExpect(jsonPath("$.name").value("Updated HR"));
    }

    @Test
    void deleteDepartment_ShouldBeSuccessful() throws Exception {
        Long departmentId = 1L;

        doNothing().when(departmentCommandService).deleteDepartmentById(departmentId);

        mockMvc.perform(delete("/api/departments/{id}", departmentId))
                .andExpect(status().isNoContent());

        verify(departmentCommandService, times(1)).deleteDepartmentById(departmentId);
    }
}
