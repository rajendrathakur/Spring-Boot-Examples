package com.springboot.examples.repository;

import com.springboot.examples.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public UUID saveEmployee(Employee employee) {
        employees.add(employee);
        return employee.getId();
    }

    public Employee getEmployee(int index) {
        return employees.get(index);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee updateEmployee(Employee employee, int index) {
        employees.set(index, employee);
        return employee;
    }

    public void deleteEmployee(int index) {
        employees.remove(index);
    }
}
