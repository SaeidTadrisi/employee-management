package com.example.employee.employee_management.service;


import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.exception.DuplicateEntityException;
import com.example.employee.employee_management.exception.EntityNotFoundException;
import com.example.employee.employee_management.model.Position;
import com.example.employee.employee_management.repository.PositionRepository;
import jakarta.transaction.Transactional;
import com.example.employee.employee_management.mapper.PositionMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PositionCommandService {

    PositionRepository positionRepository;
    PositionMapper positionMapper;

    public PositionCommandService(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public PositionDTO createPosition(PositionDTO positionDTO){

        Position positionMapperEntity = positionMapper.toEntity(positionDTO);

        if (positionRepository.existsByName(positionMapperEntity.getTitle())){
            throw new DuplicateEntityException("Position with name '" + positionDTO.getTitle() + "' already exists");
        }
        return positionMapper.toDTO(positionRepository.save(positionMapperEntity));
    }

    public PositionDTO updatePosition(Long id, PositionDTO updatedPositionDTO){

        Position positionMapperEntity = positionMapper.toEntity(updatedPositionDTO);

        Position savedPosition = positionRepository.findById(id)
                .map(position -> {
                    position.setTitle(positionMapperEntity.getTitle());
                    position.setResponsibilities(positionMapperEntity.getResponsibilities());
                    return positionRepository.save(position);
                }).orElseThrow(() -> new EntityNotFoundException("Position", id));

        return positionMapper.toDTO(savedPosition);
    }

    public void deletePositionById(Long id){
        if (!positionRepository.existsById(id)){
        throw new EntityNotFoundException("Position", id);
    }
        positionRepository.deleteById(id);
    }
}
