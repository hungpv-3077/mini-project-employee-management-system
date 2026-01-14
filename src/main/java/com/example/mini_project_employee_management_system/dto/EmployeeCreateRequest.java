package com.example.mini_project_employee_management_system.dto;

import com.example.mini_project_employee_management_system.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @UniqueEmail
    private String email;

    @NotNull(message = "DepartmentId is required")
    private Long departmentId;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
