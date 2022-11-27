package com.springboot.examples.service;

import com.springboot.examples.domain.EmployeeInfo;

public interface EmployeeService {
    public EmployeeInfo createEmployee(EmployeeInfo employeeInfo);
    public EmployeeInfo fetchEmployee(long id);

}
