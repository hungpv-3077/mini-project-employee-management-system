package com.example.mini_project_employee_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mini_project_employee_management_system.dto.DepartmentStatistics;
import com.example.mini_project_employee_management_system.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(e.department.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Employee> searchByNameOrDepartment(@Param("search") String search);

    Optional<Employee> findByEmail(String email);

    @Query("SELECT new com.example.mini_project_employee_management_system.dto.DepartmentStatistics(d.name, COUNT(e)) " +
           "FROM Employee e JOIN e.department d GROUP BY d.name ORDER BY COUNT(e) DESC")
    List<DepartmentStatistics> countEmployeesByDepartment();
}
