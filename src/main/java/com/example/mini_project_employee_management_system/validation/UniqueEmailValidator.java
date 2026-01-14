package com.example.mini_project_employee_management_system.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mini_project_employee_management_system.repository.EmployeeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private EmployeeRepository employeeRepository;

    private boolean allowSameId;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.allowSameId = constraintAnnotation.allowSameId();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.trim().isEmpty()) {
            return true;
        }
        
        if (allowSameId) {
            return true;
        }
        
        return !employeeRepository.findByEmail(email).isPresent();
    }
}
