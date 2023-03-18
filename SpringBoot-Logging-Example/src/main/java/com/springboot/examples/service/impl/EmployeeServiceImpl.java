package com.springboot.examples.service.impl;

import com.springboot.examples.controller.EmployeeController;
import com.springboot.examples.entity.Employee;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> fetchEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Resource id %d not found in our records", id)));
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteEmployees() {
        employeeRepository.deleteAll();
    }

    @Override
    public List<EmployeeDto> fetchEmployeesByCity(String city) {
        List<Employee> employees = employeeRepository.findByCity(city);
        return employees.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto fetchEmployee(Long id) {
        Integer.parseInt("abc");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return convertEntityToModel(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        convertModelToEntity(employeeDto, employee, id);
        employeeRepository.save(employee);
        return convertEntityToModel(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.trace("This is a trace message, Employee Name from Employee Service : {}", employeeDto.getName());
        log.debug("This is a debug message, Employee Name from Employee Service: {}", employeeDto.getName());
        log.info("This is a info message, Employee Name from Employee Service : {}", employeeDto.getName());
        log.warn("This is a warn message, Employee Name from Employee Service: {}", employeeDto.getName());
        log.error("This is a error message, Employee Name from Employee Service: {}", employeeDto.getName());
        Employee employee = convertModelToEntity(employeeDto);
        employeeRepository.save(employee);
        return convertEntityToModel(employee);
    }

    private EmployeeDto convertEntityToModel(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee convertModelToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }


    private void convertModelToEntity(EmployeeDto employeeDto, Employee employee, Long id) {
         modelMapper.map(employeeDto, employee);
         employee.setId(id);
    }
}
