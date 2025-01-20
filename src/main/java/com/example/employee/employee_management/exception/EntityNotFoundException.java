package com.example.employee.employee_management.exception;

public class EntityNotFoundException extends RuntimeException {

    private String entityName;
    private Long entityId;

    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + "with ID" + entityId + "Not found.");
        this.entityName = entityName;
        this.entityId = entityId;
    }
}
