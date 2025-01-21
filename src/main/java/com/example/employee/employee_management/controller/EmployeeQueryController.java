package com.example.employee.employee_management.controller;

import com.example.employee.employee_management.dto.EmployeeDTO;
import com.example.employee.employee_management.service.EmployeeQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeQueryController {

    EmployeeQueryService queryService;

    public EmployeeQueryController(EmployeeQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.findEmployeeById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(queryService.findAllEmployees());
    }
}
