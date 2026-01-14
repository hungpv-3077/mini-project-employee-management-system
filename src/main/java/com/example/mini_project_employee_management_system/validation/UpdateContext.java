package com.example.mini_project_employee_management_system.validation;

public class UpdateContext {
    private static final ThreadLocal<Long> employeeId = new ThreadLocal<>();

    public static void setEmployeeId(Long id) {
        employeeId.set(id);
    }

    public static Long getEmployeeId() {
        return employeeId.get();
    }

    public static void clear() {
        employeeId.remove();
    }
}
