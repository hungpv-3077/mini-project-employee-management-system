package com.example.mini_project_employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mini_project_employee_management_system.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
