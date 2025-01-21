package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.service.PositionCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionCommandController {

    PositionCommandService commandService;

    public PositionCommandController(PositionCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<PositionDTO> createPosition(@Valid @RequestBody PositionDTO positionDTO){
        return ResponseEntity.ok(commandService.createPosition(positionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable Long id
            , @Valid @RequestBody PositionDTO updatedPositionDTO){

        return ResponseEntity.ok(commandService.updatePosition(id, updatedPositionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id){
        commandService.deletePositionById(id);
        return ResponseEntity.noContent().build();
    }
}
