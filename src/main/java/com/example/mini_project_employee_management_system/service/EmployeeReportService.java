package com.example.mini_project_employee_management_system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.mini_project_employee_management_system.dto.DepartmentStatistics;
import com.example.mini_project_employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeReportService {

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeReportService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "employeeCount", key = "'total'")
    public long getTotalEmployees() {
        log.info("Querying database for total employees");
        return employeeRepository.count();
    }

    public List<DepartmentStatistics> getEmployeesByDepartment() {
        log.info("Querying database for employees by department");
        return employeeRepository.countEmployeesByDepartment();
    }
}
