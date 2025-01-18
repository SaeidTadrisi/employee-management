package com.example.employee.employee_management.service;

import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PositionService {

    PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(Position position){
        return positionRepository.save(position);
    }

    public Optional<Position> findPositionById(Long id){
        return positionRepository.findById(id);
    }

    public Position updatePosition(Long id, Position updatedPosition){
        return positionRepository.findById(id)
                .map(position -> {
                    position.setTitle(updatedPosition.getTitle());
                    position.setResponsibilities(updatedPosition.getResponsibilities());
                    return positionRepository.save(position);
                }).orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
    }

    public void deletePositionById(Long id){
        positionRepository.deleteById(id);
    }


    public Optional<Position> findPositionByIdWithEmployees(Long id){
        return positionRepository.findPositionByIdWithEmployees(id);
    }
}