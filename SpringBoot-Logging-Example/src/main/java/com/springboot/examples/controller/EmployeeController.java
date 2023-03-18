package com.springboot.examples.controller;

import com.springboot.examples.exception.ErrorMessage;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto) {
        log.trace("This is a trace message, Employee Name from EmployeeController : {}", employeeDto.getName());
        log.debug("This is a debug message, Employee Name from EmployeeController: {}", employeeDto.getName());
        log.info("This is a info message, Employee Name from EmployeeController: {}", employeeDto.getName());
        log.warn("This is a warn message, Employee Name from EmployeeController: {}", employeeDto.getName());
        log.error("This is a error message, Employee Name from EmployeeController: {}", employeeDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping(value = "{id}")
    public EmployeeDto fetchEmployee(@PathVariable Long id) {
        return employeeService.fetchEmployee(id);
    }

    @GetMapping("query")
    public List<EmployeeDto> fetchEmployees(@RequestParam(required = false) String city) {
        if (city != null) {
            return employeeService.fetchEmployeesByCity(city);
        } else {
            return employeeService.fetchEmployees();
        }

    }

    @PutMapping(value = "{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployees() {
        employeeService.deleteEmployees();
    }




}
