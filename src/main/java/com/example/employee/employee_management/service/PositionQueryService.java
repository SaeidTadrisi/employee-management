package com.example.employee.employee_management.service;

import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import mapper.PositionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionQueryService {

    PositionRepository positionRepository;
    PositionMapper positionMapper;

    public PositionQueryService(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public Position findPositionById(Long id){
        return positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position", id));
    }

    public List<Position> findAllPositions(){
        return positionRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Position findPositionByIdWithEmployees(Long id){
        return positionRepository.findPositionByIdWithEmployees(id)
        .orElseThrow(() -> new EntityNotFoundException("Position", id));
    }

}
