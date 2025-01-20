package com.example.employee.employee_management.service;


import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PositionCommandService {

    PositionRepository positionRepository;

    public PositionCommandService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(Position position){
        if (positionRepository.existsByName(position.getTitle())){
            throw new DuplicateEntityException("Position with name '" + position.getTitle() + "' already exists");
        }
        return positionRepository.save(position);
    }

    public Position updatePosition(Long id, Position updatedPosition){
        return positionRepository.findById(id)
                .map(position -> {
                    position.setTitle(updatedPosition.getTitle());
                    position.setResponsibilities(updatedPosition.getResponsibilities());
                    return positionRepository.save(position);
                }).orElseThrow(() -> new EntityNotFoundException("Position", id));
    }

    public void deletePositionById(Long id){
        if (!positionRepository.existsById(id)){
        throw new EntityNotFoundException("Position", id);
    }
        positionRepository.deleteById(id);
    }
}
