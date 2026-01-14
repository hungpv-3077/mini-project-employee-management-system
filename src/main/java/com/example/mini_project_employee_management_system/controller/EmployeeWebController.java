package com.example.mini_project_employee_management_system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mini_project_employee_management_system.dto.EmployeeCreateRequest;
import com.example.mini_project_employee_management_system.entity.Department;
import com.example.mini_project_employee_management_system.entity.Employee;
import com.example.mini_project_employee_management_system.repository.DepartmentRepository;
import com.example.mini_project_employee_management_system.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    public EmployeeWebController(EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/list")
    public String listEmployees(@RequestParam(required = false) String search, Model model) {
        List<Employee> employees = employeeService.findAll(search);
        model.addAttribute("employees", employees);
        model.addAttribute("search", search);
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeCreateRequest());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") EmployeeCreateRequest request, 
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/add";
        }
        
        try {
            employeeService.add(request);
            return "redirect:/employees/list?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("departments", departmentRepository.findAll());
            return "employees/add";
        }
    }
}
