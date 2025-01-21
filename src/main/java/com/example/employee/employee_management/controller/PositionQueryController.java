package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.dto.PositionDTO;
import com.example.employee.employee_management.service.PositionQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionQueryController {

    PositionQueryService queryService;

    public PositionQueryController(PositionQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.findPositionById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PositionDTO>> getAllPositions(){
        return ResponseEntity.ok(queryService.findAllPositions());
    }
}
