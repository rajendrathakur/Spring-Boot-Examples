package com.springboot.examples.controller;

import com.springboot.examples.exception.ErrorMessage;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import com.springboot.examples.service.impl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
public class EmployeeController {

    private static final Logger log =
            Logger.getLogger(EmployeeController.class.getName());
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping(value = "{id}")
    public EmployeeDto fetchEmployee(@PathVariable Long id) {
        log.info("About to fetch employee with id: "+ id + " from the controller");
        return employeeService.fetchEmployee(id);
    }

    @GetMapping("query")
    public List<EmployeeDto> fetchEmployees(@RequestParam(required = false) String city) {
        if (city != null) {
            return employeeService.fetchEmployeesByCity(city);
        } else {
            log.info("About to fetch all employees from the controller");
            return employeeService.fetchEmployees();
        }
    }

    @PutMapping(value = "{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        log.info("About to update employee with id: "+ id + " from the controller");
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
