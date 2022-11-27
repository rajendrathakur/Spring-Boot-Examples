package com.springboot.examples.service;

import com.springboot.examples.domain.EmployeeInfo;
import com.springboot.examples.domain.LaptopInfo;
import com.springboot.examples.entity.Employee;
import com.springboot.examples.exception.EmployeeNotFoundException;
import com.springboot.examples.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeInfo fetchEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return convertEmployeeToEmployeeInfo(employee);
    }

    @Override
    public EmployeeInfo createEmployee(EmployeeInfo employeeInfo) {
        Employee employee = modelMapper.map(employeeInfo, Employee.class);
        employeeRepository.save(employee);
        return convertEmployeeToEmployeeInfo(employee);
    }

    private EmployeeInfo convertEmployeeToEmployeeInfo(Employee employee) {
        EmployeeInfo employeeInfo = modelMapper.map(employee, EmployeeInfo.class);
        employeeInfo.setLaptopInfo(modelMapper.map(employee.getLaptop(), LaptopInfo.class));
        return employeeInfo;
    }
}
