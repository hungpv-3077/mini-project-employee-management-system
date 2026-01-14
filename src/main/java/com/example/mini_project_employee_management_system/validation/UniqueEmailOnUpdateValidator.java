package com.example.mini_project_employee_management_system.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mini_project_employee_management_system.entity.Employee;
import com.example.mini_project_employee_management_system.repository.EmployeeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailOnUpdateValidator implements ConstraintValidator<UniqueEmailOnUpdate, String> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (email == null || email.trim().isEmpty()) {
            return true;
        }

        Long currentEmployeeId = UpdateContext.getEmployeeId();
        if (currentEmployeeId == null) {
            return true;
        }

        Optional<Employee> existingEmployee = employeeRepository.findByEmail(email);
        System.out.println("Existing Employee for email " + email + ": " + existingEmployee);
        return !existingEmployee.isPresent() || existingEmployee.get().getId().equals(currentEmployeeId);
    }
}
