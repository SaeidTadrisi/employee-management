package com.example.employee.employee_management.controller.command;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.service.command.PositionCommandService;
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

public class PositionCommandControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private PositionCommandService positionCommandService;

    @InjectMocks
    private PositionCommandController positionCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(positionCommandController).build();
    }
    @Test
    void createPosition_ShouldReturnCreatedPosition() throws Exception {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop Software");
        dto.setId(1L);

        when(positionCommandService.createPosition(any(PositionDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/positions/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Developer"))
                .andExpect(jsonPath("$.responsibilities").value("Develop Software"));
    }

    @Test
    void updatePosition_ShouldReturnUpdatedPosition() throws Exception {
        Long positionId = 1L;
        PositionDTO updatedDto = new PositionDTO();
        updatedDto.setTitle("Developer2");
        updatedDto.setResponsibilities("Develop Software");
        updatedDto.setId(positionId);

        when(positionCommandService.updatePosition(eq(1L), any(PositionDTO.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/positions/{id}", positionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(positionId))
                .andExpect(jsonPath("$.title").value("Developer2"))
                .andExpect(jsonPath("$.responsibilities").value("Develop Software"));
    }

    @Test
    void deletePosition_ShouldBeSuccessful() throws Exception {
        Long positionId = 1L;

        doNothing().when(positionCommandService).deletePositionById(positionId);

        mockMvc.perform(delete("/api/positions/{id}", positionId))
                .andExpect(status().isNoContent());

        verify(positionCommandService, times(1)).deletePositionById(positionId);
    }
}
