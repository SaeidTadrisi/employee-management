package com.example.employee.employee_management.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String details;

    public ErrorMessage(HttpStatus status, String error, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = error;
        this.details = details;
    }

    // Getters and setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
