package com.example.mini_project_employee_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mini_project_employee_management_system.dto.DepartmentStatistics;
import com.example.mini_project_employee_management_system.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    @Query("SELECT new com.example.mini_project_employee_management_system.dto.DepartmentStatistics(d.name, COUNT(e)) " +
           "FROM Department d LEFT JOIN Employee e ON e.department.id = d.id " +
           "GROUP BY d.id, d.name ORDER BY COUNT(e) DESC")
    List<DepartmentStatistics> getDepartmentStatistics();
}
