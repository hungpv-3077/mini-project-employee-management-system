package com.example.mini_project_employee_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mini_project_employee_management_system.dto.EmployeeCreateRequest;
import com.example.mini_project_employee_management_system.dto.EmployeeUpdateRequest;
import com.example.mini_project_employee_management_system.entity.Employee;
import com.example.mini_project_employee_management_system.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> findAll(
            @RequestParam(required = false) String search) {
        return employeeService.findAll(search);
    }

    @PostMapping()
    public Map<String, String> add(@Valid @RequestBody EmployeeCreateRequest request) {
        return employeeService.add(request);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }
}
