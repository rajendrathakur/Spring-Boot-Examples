package com.springboot.examples.service;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {
  EmployeeDto createEmployee(EmployeeDto employeeDto);

  EmployeeDto fetchEmployee(Long id);

  List<EmployeeDto> fetchEmployees();

  List<EmployeeDto> fetchEmployeesByCity(String name);

  EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);

  void deleteEmployee(Long id);

  void deleteAllEmployee();




}


