package com.example.employee.employee_management.controller.command;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.service.command.DepartmentCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentCommandController {

    private final DepartmentCommandService commandService;

    public DepartmentCommandController(DepartmentCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(commandService.createDepartment(departmentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id
            , @Valid @RequestBody DepartmentDTO updatedDepartmentDTO){
        return ResponseEntity.ok(commandService.updateDepartment(id, updatedDepartmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        commandService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

}
