package com.example.mini_project_employee_management_system.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, String> errors;

    public ValidationException(String field, String message) {
        super(message);
        this.errors = new HashMap<>();
        this.errors.put(field, message);
    }

    public ValidationException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
