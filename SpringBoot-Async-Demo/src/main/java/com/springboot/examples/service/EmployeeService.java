package com.springboot.examples.service;

import com.springboot.examples.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

  EmployeeDto createEmployeeSynchronousWay(EmployeeDto employeeDto) throws InterruptedException;

  void employeePFCalculation(long employeeId) throws InterruptedException;

  long createEmployee(EmployeeDto employeeDto) ;


  EmployeeDto fetchEmployee(Long id);

}


