package com.example.mini_project_employee_management_system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mini_project_employee_management_system.dto.EmployeeCreateRequest;
import com.example.mini_project_employee_management_system.dto.EmployeeUpdateRequest;
import com.example.mini_project_employee_management_system.entity.Department;
import com.example.mini_project_employee_management_system.entity.Employee;
import com.example.mini_project_employee_management_system.exception.ValidationException;
import com.example.mini_project_employee_management_system.repository.DepartmentRepository;
import com.example.mini_project_employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final UtilityService utilityService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private static final Logger log
            = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(
            UtilityService utilityService,
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository
    ) {
        this.utilityService = utilityService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createEmployee(String name) {
        String code = utilityService.generateEmployeeCode();
        String formattedName = utilityService.formatName(name);

        return formattedName + " | " + code;
    }

    public List<Employee> findAll(String search) {
        if (search != null && !search.trim().isEmpty()) {
            return this.employeeRepository.searchByNameOrDepartment(search.trim());
        }

        return this.employeeRepository.findAll();
    }

    public Map<String, String> add(EmployeeCreateRequest request) {
        log.info("Creating employee: name={}, email={}",
                request.getName(), request.getEmail());
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Employee employee = new Employee();
        employee.setCode(utilityService.generateEmployeeCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        employeeRepository.save(employee);

        log.info("Employee created successfully with id={}",
                employee.getId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee added successfully");
        return response;
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    public Map<String, String> update(Long id, EmployeeUpdateRequest request) {
        log.info("Updating employee id={}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        // Update name if provided
        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            employee.setName(request.getName());
        }

        log.info("Employee updated: id={}, name={}",
                id, employee.getName());

        // Update email if provided and validate uniqueness
        if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
            Optional<Employee> existingEmployee = employeeRepository.findByEmail(request.getEmail());
            if (existingEmployee.isPresent() && !existingEmployee.get().getId().equals(id)) {
                throw new ValidationException("email", "Email already exists");
            }
            employee.setEmail(request.getEmail());
        }

        // Update department if provided
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            employee.setDepartment(department);
        }

        employeeRepository.save(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee updated successfully");
        return response;
    }

    public Map<String, String> delete(Long id) {
        log.info("Deleting employee id={}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        employeeRepository.delete(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        log.info("Employee deleted successfully id={}", id);
        return response;
    }
}
