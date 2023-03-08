package com.springboot.examples.service.impl;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.juli.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "EmployeeCache")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log =
            Logger.getLogger(EmployeeServiceImpl.class.getName());

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    @Cacheable(cacheNames = "EmployeeListCache")
    public List<EmployeeDto> fetchEmployees() {
        log.info("About to fetch all employees from the database");
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Resource id %d not found in our records", id)));
        employeeRepository.delete(employee);
    }

    @Override
    @CacheEvict(cacheNames = "EmployeeListCache")
    public void deleteEmployees() {
        log.info("Delete all employees from the database");
        employeeRepository.deleteAll();
    }

    @Override
    public List<EmployeeDto> fetchEmployeesByCity(String city) {
        List<Employee> employees = employeeRepository.findByCity(city);
        return employees.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#id", condition = "#id<3")
    public EmployeeDto fetchEmployee(Long id) {
        log.info("About to fetch employee with id: "+ id + " from the database");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return convertEntityToModel(employee);
    }

    @Override
    @CachePut(key = "#id")
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        log.info("About to update employee with id: "+ id + " from the service");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        log.info("Updated employee city: "+ employee.getCity());
        convertModelToEntity(employeeDto, employee, id);
        log.info("Updated employee city before save: "+ employee.getCity());
        employeeRepository.save(employee);
        log.info("Updated employee with id: "+ id + " to the database");
        return convertEntityToModel(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
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
