package com.springboot.examples.service;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

  EmployeeDto createEmployee(EmployeeDto employeeDto);


  EmployeeDto fetchEmployee(Long id);


  EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);


  List<EmployeeDto> fetchEmployeesByCity(String city);

  List<EmployeeDto> fetchEmployees();

  void deleteEmployee(Long id);

  void deleteEmployees();




}


