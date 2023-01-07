package com.springboot.examples.controller;

import com.springboot.examples.model.Employee;
import com.springboot.examples.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("/employees/")
@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable UUID id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> saveEmployee(@RequestBody Employee employee) {
        UUID uuid = employeeService.saveEmployee(employee);
        return new ResponseEntity<UUID>(uuid, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable UUID id) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> fetchEmployees() {
        return new ResponseEntity<>(employeeService.fetchEmployees(), HttpStatus.OK);
    }
}
