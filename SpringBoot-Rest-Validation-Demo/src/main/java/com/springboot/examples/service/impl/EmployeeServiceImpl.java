package com.springboot.examples.service.impl;

import com.springboot.examples.domain.Employee;
import com.springboot.examples.exception.EmployeeNotFoundException;
import com.springboot.examples.model.AddressDto;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        return convertEntityToModel(employee);
    }

    @Override
    public EmployeeDto fetchEmployee(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return convertEntityToModel(employee);
    }

    public EmployeeDto convertEntityToModel(Employee employee) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        EmployeeDto employeeDto =  modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
