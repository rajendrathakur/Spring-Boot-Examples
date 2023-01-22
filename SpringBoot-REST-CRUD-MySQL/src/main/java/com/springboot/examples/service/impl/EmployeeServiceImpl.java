package com.springboot.examples.service.impl;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<EmployeeDto> fetchEmployeesByCity(String name) {
        List<Employee> employees =  employeeRepository.findByCity(name);
        return employees.stream().map(emp -> convertEntityToModel(emp)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> fetchEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map( this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        convertModelToEntity(employeeDto, employee, id);
        employeeRepository.save(employee);
        return convertEntityToModel(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployee() {
        employeeRepository.deleteAll();
    }

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = convertModelToEntity(employeeDto);
        employeeRepository.save(employee);
        return convertEntityToModel(employee);
    }

    @Override
    public EmployeeDto fetchEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return convertEntityToModel(employee);
    }

    private Employee convertModelToEntity(EmployeeDto employeeDto) {
       return  modelMapper.map(employeeDto, Employee.class);
    }

    private void convertModelToEntity(EmployeeDto employeeDto, Employee employee, Long id) {
         modelMapper.map(employeeDto, employee);
         employee.setId(id);
    }

    private EmployeeDto convertEntityToModel(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }


}
