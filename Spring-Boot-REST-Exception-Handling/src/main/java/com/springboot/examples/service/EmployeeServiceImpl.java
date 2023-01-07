package com.springboot.examples.service;

import com.springboot.examples.exception.EmployeeNotFoundException;
import com.springboot.examples.model.Employee;
import com.springboot.examples.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee updateEmployee(Employee employee, UUID id) {
        return employeeRepository.updateEmployee(employee, findIndexById(id));
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteEmployee(findIndexById(id));
    }

    @Override
    public List<Employee> fetchEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public UUID saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee getEmployee(UUID id) {
        return employeeRepository.getEmployee(findIndexById(id));
    }

    private int findIndexById(UUID id) {
        return IntStream.range(0, employeeRepository.getEmployees().size())
                .filter(index -> employeeRepository.getEmployees().get(index).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
