package com.springboot.examples.service;

import com.springboot.examples.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    public UUID saveEmployee(Employee employee);

    public Employee getEmployee(UUID id);

    public Employee updateEmployee(Employee employee, UUID id);

    public void deleteEmployee(UUID id);

    public List<Employee> fetchEmployees();
}
