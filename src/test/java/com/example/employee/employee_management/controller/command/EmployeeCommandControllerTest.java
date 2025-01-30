package com.example.employee.employee_management.controller.command;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.service.command.EmployeeCommandService;
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

public class EmployeeCommandControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private EmployeeCommandService employeeCommandService;

    @InjectMocks
    private EmployeeCommandController employeeCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeCommandController).build();
    }
    @Test
    void createEmployee_ShouldReturnCreatedEmployee() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("John Doe");
        dto.setSalary(50000.0);
        dto.setEmail("John@Doe.com");
        dto.setId(1L);

        when(employeeCommandService.createEmployee(any(EmployeeDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/employees/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.salary").value(50000.0))
                .andExpect(jsonPath("$.email").value("John@Doe.com"));
    }

    @Test
    void updateEmployee_ShouldReturnUpdatedEmployee() throws Exception {
        Long employeeId = 1L;
        EmployeeDTO updatedDto = new EmployeeDTO();
        updatedDto.setName("Sam Altman");
        updatedDto.setSalary(30000.0);
        updatedDto.setEmail("Sam@Altman.com");
        updatedDto.setId(employeeId);

        when(employeeCommandService.updateEmployee(eq(1L), any(EmployeeDTO.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sam Altman"))
                .andExpect(jsonPath("$.salary").value(30000.0))
                .andExpect(jsonPath("$.email").value("Sam@Altman.com"));
    }

    @Test
    void assignDepartment_ShouldAssignDepartment() throws Exception {
        Long employeeId = 1L;
        Long departmentId = 10L;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Sam Altman");
        dto.setId(employeeId);
        dto.setDepartmentName("HR");

        when(employeeCommandService.assignDepartment(employeeId, departmentId)).thenReturn(dto);

        mockMvc.perform(patch("/api/employees/{employeeId}/assign-department/{departmentId}", employeeId, departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sam Altman"))
                .andExpect(jsonPath("$.departmentName").value("HR"));
    }

    @Test
    void assignPosition_ShouldAssignPosition() throws Exception {
        Long employeeId = 1L;
        Long positionId = 10L;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Sam Altman");
        dto.setId(employeeId);
        dto.setPositionTitle("Developer");

        when(employeeCommandService.assignPosition(employeeId, positionId)).thenReturn(dto);

        mockMvc.perform(patch("/api/employees/{employeeId}/assign-position/{positionId}", employeeId, positionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sam Altman"))
                .andExpect(jsonPath("$.positionTitle").value("Developer"));
    }

    @Test
    void deleteEmployee_ShouldBeSuccessful() throws Exception {
        Long employeeId = 1L;

        doNothing().when(employeeCommandService).deleteEmployeeById(employeeId);

        mockMvc.perform(delete("/api/employees/{id}", employeeId))
                .andExpect(status().isNoContent());

        verify(employeeCommandService, times(1)).deleteEmployeeById(employeeId);
    }
}
