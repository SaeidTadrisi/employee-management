package com.example.employee.employee_management.controller.query;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.service.query.PositionQueryService;
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

public class PositionQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PositionQueryService positionQueryService;

    @InjectMocks
    private PositionQueryController positionQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(positionQueryController).build();
    }
    @Test
    void getPositionById_ShouldReturnPosition() throws Exception {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop Software");
        dto.setId(1L);

        when(positionQueryService.findPositionById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/positions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Developer"));
    }

    @Test
    void getAllPositions_ShouldReturnPositions() throws Exception {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop Software");

        PositionDTO dto2 = new PositionDTO();
        dto2.setTitle("Tester");
        dto2.setResponsibilities("Test Software");

        when(positionQueryService.findAllPositions()).thenReturn(List.of(dto, dto2));

        mockMvc.perform(get("/api/positions/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Developer"))
                .andExpect(jsonPath("$[1].title").value("Tester"));
    }

}
