package com.example.employee.employee_management.service.command;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.mapper.PositionMapper;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PositionCommandServiceTest {

    @Mock
    PositionRepository positionRepository;

    @Mock
    PositionMapper positionMapper;

    @InjectMocks
    PositionCommandService positionCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPosition_ShouldCreatePosition() {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setTitle("Developer");
        positionDTO.setResponsibilities("Develop Software");

        Position position = new Position("Developer", "Develop Software");

        when(positionMapper.toEntity(positionDTO)).thenReturn(position);
        when(positionRepository.existsByTitle("Developer")).thenReturn(false);
        when(positionRepository.save(any(Position.class))).thenReturn(position);
        when(positionMapper.toDTO(any(Position.class))).thenReturn(positionDTO);

        PositionDTO result = positionCommandService.createPosition(positionDTO);

        assertNotNull(result);
        assertEquals("Developer", result.getTitle());
        verify(positionRepository, times(1)).save(position);
    }

    @Test
    void createPosition_ShouldThrowDuplicateEntityException() {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setTitle("Developer");
        positionDTO.setResponsibilities("Develop Software");

        Position position = new Position("Developer", "Develop Software");

        when(positionRepository.existsByTitle("Developer")).thenReturn(true);
        when(positionMapper.toEntity(positionDTO)).thenReturn(position);

        assertThrows(DuplicateEntityException.class, () -> positionCommandService.createPosition(positionDTO));
        verify(positionRepository, never()).save(any());
    }

    @Test
    void updateDepartment_ShouldUpdateDepartment() {
        Long positionId = 1L;
        Position existingPosition = new Position("Developer", "Develop Software");
        existingPosition.setId(positionId);

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setTitle("Developer");
        positionDTO.setResponsibilities("Develop Software");

        when(positionRepository.findById(positionId)).thenReturn(Optional.of(existingPosition));
        when(positionMapper.toEntity(positionDTO)).thenReturn(new Position("Developer", "Develop Software"));
        when(positionRepository.save(any(Position.class))).thenReturn(existingPosition);
        when(positionMapper.toDTO(any(Position.class))).thenReturn(positionDTO);

        PositionDTO result = positionCommandService.updatePosition(positionId, positionDTO);

        assertNotNull(result);
        assertEquals("Developer", result.getTitle());
        verify(positionRepository, times(1)).save(existingPosition);
    }

    @Test
    void updateDepartment_ShouldThrowEntityNotFoundException() {
        Long positionId = 1L;

        PositionDTO updatesPositionDTO = new PositionDTO();
        updatesPositionDTO.setTitle("Developer");
        updatesPositionDTO.setResponsibilities("Develop Software");

        when(positionRepository.findById(positionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                ()-> positionCommandService.updatePosition(positionId, updatesPositionDTO));
        verify(positionRepository, never()).save(any());
    }

    @Test
    void deleteDepartmentById_ShouldDeleteDepartment() {
        Long departmentId = 1L;

        when(positionRepository.existsById(departmentId)).thenReturn(true);
        positionCommandService.deletePositionById(departmentId);

        verify(positionRepository, times(1)).deleteById(departmentId);
    }

    @Test
    void deleteDepartmentById_ShouldThrowEntityNotFoundException() {
        Long departmentId = 1L;

        when(positionRepository.existsById(departmentId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> positionCommandService.deletePositionById(departmentId));
        verify(positionRepository, never()).deleteById(departmentId);
    }
}