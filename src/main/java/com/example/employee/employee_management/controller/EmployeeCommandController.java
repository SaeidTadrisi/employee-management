package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.service.command.EmployeeCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeCommandController {

    EmployeeCommandService commandService;

    public EmployeeCommandController(EmployeeCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(commandService.createEmployee(employeeDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id
            , @Valid @RequestBody EmployeeDTO updateEmployeeDTO){
        return ResponseEntity.ok(commandService.updateEmployee(id, updateEmployeeDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        commandService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{employeeId}/assign-department/{departmentId}")
    public ResponseEntity<EmployeeDTO> assignDepartment(@PathVariable Long employeeId
            , @PathVariable Long departmentId){

        return ResponseEntity.ok(commandService.assignDepartment(employeeId, departmentId));
    }

    @PatchMapping("{employeeId}/assign-position/{positionId}")
    public ResponseEntity<EmployeeDTO> assignPosition(@PathVariable Long employeeId
            , @PathVariable Long positionId){
        return ResponseEntity.ok(commandService.assignPosition(employeeId, positionId));
    }
}
