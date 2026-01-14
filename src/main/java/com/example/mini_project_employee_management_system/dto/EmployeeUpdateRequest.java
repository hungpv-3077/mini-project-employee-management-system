package com.example.mini_project_employee_management_system.dto;

import jakarta.validation.constraints.Email;

public class EmployeeUpdateRequest {

    private String name;

    @Email(message = "Email is not valid")
    private String email;

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
