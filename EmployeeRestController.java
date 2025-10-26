package com.example.employee_management.controller;

import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    // Get all employees (REST)
    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new employee
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee create(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
