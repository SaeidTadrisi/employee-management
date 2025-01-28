package com.example.employee.employee_management.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + " with ID " + entityId + " Not found.");
    }
}
