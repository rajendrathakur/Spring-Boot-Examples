package com.springboot.examples.service;

import com.springboot.examples.model.EmployeeDto;


public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public EmployeeDto fetchEmployee(int id);
}
