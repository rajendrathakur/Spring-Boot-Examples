package com.springboot.examples.service.impl;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log =
            Logger.getLogger(EmployeeServiceImpl.class.getName());
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployeeSynchronousWay(EmployeeDto employeeDto) throws InterruptedException {
        Employee employee = convertModelToEntity(employeeDto);
        employee.setStatus("Accepted");
        employee.setStatusCode(202);
        employeeRepository.save(employee);
        log.info("This request takes 8 seconds with thread: "+ Thread.currentThread().getName());
        Thread.sleep(15000l);
        employee.setPfAmount(250000);
        employee.setPensionAmount(150000);
        employee.setStatus("Completed");
        employee.setStatusCode(200);
        log.info("Finally request processing finished with thread: "+ Thread.currentThread().getName());
        return convertEntityToModel(employee);
    }

    @Override
    @Async
    public void employeePFCalculation(long employeeId) throws InterruptedException {
        log.info("Employee EPF Calculation started with thread: "+ Thread.currentThread().getName());
        Thread.sleep(15000l);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(employeeId));
        employee = null;
        employee.setPfAmount(250000);
        employee.setPensionAmount(150000);
        employee.setStatus("Completed");
        employee.setStatusCode(200);
    }

    @Override
    public long createEmployee(EmployeeDto employeeDto) {
        Employee employee = convertModelToEntity(employeeDto);
        employee.setStatus("Accepted");
        employee.setStatusCode(202);
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public EmployeeDto fetchEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return convertEntityToModel(employee);
    }

    private EmployeeDto convertEntityToModel(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee convertModelToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

}
