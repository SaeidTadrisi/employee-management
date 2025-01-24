package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.service.query.DepartmentQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentQueryController {

    private final DepartmentQueryService queryService;

    public DepartmentQueryController(DepartmentQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.findDepartmentById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(queryService.findAllDepartments());
    }
}
