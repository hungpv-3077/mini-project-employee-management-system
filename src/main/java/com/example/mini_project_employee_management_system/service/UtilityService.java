package com.example.mini_project_employee_management_system.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    public String formatName(String name) {
        return name.trim().toUpperCase();
    }

    public String generateEmployeeCode() {
        return "EMP-" + System.currentTimeMillis();
    }
}
