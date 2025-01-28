package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.model.Position;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionMapper {

    public PositionDTO toDTO(Position position){
        if (position == null){
            return null;
        }
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setTitle(position.getTitle());
        positionDTO.setResponsibilities(position.getResponsibilities());
        return positionDTO;
    }

    public List<PositionDTO> toDTOList(List<Position> positions){
        if (positions == null){
            return Collections.emptyList();
        }
        return positions.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public Position toEntity(PositionDTO positionDTO){
        if (positionDTO == null){
            return null;
        }
        Position position = new Position();
        position.setTitle(positionDTO.getTitle());
        position.setResponsibilities(positionDTO.getResponsibilities());
        return position;
    }
}
