package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionMapperTest {

    PositionMapper positionMapper = new PositionMapper();

    @Test
    void toDTO_ShouldMapPositionToDTO() {
        Position position = new Position("Developer", "Develop Software");
        position.setId(1L);

        PositionDTO dto = positionMapper.toDTO(position);

        assertNotNull(dto);
        assertEquals("Developer", dto.getTitle());
        assertEquals("Develop Software", dto.getResponsibilities());
        assertEquals(1L , dto.getId());
    }

    @Test
    void toEntity_ShouldMapDTOToPosition() {
        PositionDTO dto = new PositionDTO();
        dto.setTitle("Developer");
        dto.setResponsibilities("Develop Software");

        Position position = positionMapper.toEntity(dto);

        assertNotNull(position);
        assertEquals("Developer", position.getTitle());
        assertEquals("Develop Software", position.getResponsibilities());
    }

    @Test
    void toDTOList_ShouldMapListOfPositionsToDTOs() {
        Position position1 = new Position("Developer", "Develop Software");
        position1.setId(1L);
        Position position2 = new Position("Tester", "Test Software");
        position2.setId(2L);

        List<Position> positions = List.of(position1, position2);

        List<PositionDTO> dtoList = positionMapper.toDTOList(positions);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals("Developer", dtoList.get(0).getTitle());
        assertEquals("Develop Software", dtoList.get(0).getResponsibilities());
        assertEquals("Tester", dtoList.get(1).getTitle());
        assertEquals("Test Software", dtoList.get(1).getResponsibilities());
    }

    @Test
    void toDTOList_ShouldReturnEmptyListWhenInputIsNull() {
        List<PositionDTO> dtoList = positionMapper.toDTOList(null);

        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
