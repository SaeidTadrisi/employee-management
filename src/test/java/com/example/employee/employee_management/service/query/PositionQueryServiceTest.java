package com.example.employee.employee_management.service.query;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.PositionMapper;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PositionQueryServiceTest {

    @Mock
    PositionRepository positionRepository;

    @Mock
    PositionMapper positionMapper;

    @InjectMocks
    PositionQueryService positionQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findPositionById_ShouldReturnPositionDTO() {
        Long positionId = 1L;
        Position position = new Position("Developer", "Develop Software");

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setTitle("Developer");
        positionDTO.setResponsibilities("Develop Software");

        when(positionRepository.findById(positionId)).thenReturn(Optional.of(position));
        when(positionMapper.toDTO(position)).thenReturn(positionDTO);

        PositionDTO result = positionQueryService.findPositionById(positionId);

        assertNotNull(result);
        assertEquals("Developer", result.getTitle());
        verify(positionRepository, times(1)).findById(positionId);
        verify(positionMapper, times(1)).toDTO(position);
    }

    @Test
    void findPositionById_ShouldThrowEntityNotFoundException() {
        Long positionId = 1L;

        when(positionRepository.findById(positionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> positionQueryService.findPositionById(positionId));
        verify(positionRepository, times(1)).findById(positionId);
        verify(positionMapper, never()).toDTO(any());
    }

    @Test
    void findAllPositions_ShouldReturnListOfPositionDTOs() {
        Position developer = new Position("Developer", "Develop Software");
        Position tester = new Position("Tester", "Test Software");

        PositionDTO devDTO = new PositionDTO();
        devDTO.setTitle("Developer");
        devDTO.setResponsibilities("Develop Software");

        PositionDTO testDTO = new PositionDTO();
        testDTO.setTitle("Tester");
        testDTO.setResponsibilities("Test Software");


        when(positionRepository.findAll()).thenReturn(List.of(developer, tester));
        when(positionMapper.toDTOList(List.of(developer, tester))).thenReturn(List.of(devDTO, testDTO));

        List<PositionDTO> result = positionQueryService.findAllPositions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Developer", result.get(0).getTitle());
        assertEquals("Develop Software", result.get(0).getResponsibilities());
        assertEquals("Tester", result.get(1).getTitle());
        assertEquals("Test Software", result.get(1).getResponsibilities());
        verify(positionRepository, times(1)).findAll();
        verify(positionMapper, times(1)).toDTOList(anyList());
    }

    @Test
    void findAllDepartments_ShouldReturnEmptyList_WhenNoDepartmentsExist() {

        when(positionRepository.findAll()).thenReturn(Collections.emptyList());
        when(positionMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PositionDTO> result = positionQueryService.findAllPositions();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(positionRepository, times(1)).findAll();
        verify(positionMapper, times(1)).toDTOList(anyList());
    }
}