package com.example.employee_management.controller;

import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Home page - list all employees
    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "index";  // Thymeleaf template index.html
    }

    // Show Add Employee form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add_employee"; // Thymeleaf template add_employee.html
    }

    // Save employee (from form)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    	
        service.saveEmployee(employee);
        return "redirect:/";
    }

    // Delete employee (from list)
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/";
    }
}
