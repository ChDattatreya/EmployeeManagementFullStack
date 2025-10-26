package com.example.employee_management.service;

import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Save or update employee
    public Employee saveEmployee(Employee employee) {
        Employee saved = repository.save(employee);
        sendAsyncNotification(saved);  // simulate async notification
        return saved;
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    // Async simulation (e.g., send email)
    @Async
    public CompletableFuture<Void> sendAsyncNotification(Employee employee) {
        // Simulate delay
        try {
            Thread.sleep(2000);
            System.out.println("Async Notification: Employee added -> " + employee.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
}
