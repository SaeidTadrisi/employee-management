package com.example.employee.employee_management.service.query;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.repository.PositionRepository;
import com.example.employee.employee_management.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionQueryService {

    PositionRepository positionRepository;
    PositionMapper positionMapper;

    public PositionQueryService(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public PositionDTO findPositionById(Long id){
        return positionMapper.toDTO(positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position", id)));
    }

    public List<PositionDTO> findAllPositions(){
        return positionMapper.toDTOList(positionRepository.findAll());
    }

}
