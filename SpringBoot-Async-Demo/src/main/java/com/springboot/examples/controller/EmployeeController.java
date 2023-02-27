package com.springboot.examples.controller;

import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import com.springboot.examples.service.impl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
public class EmployeeController {
    private EmployeeService employeeService;

    private static final Logger log =
            Logger.getLogger(EmployeeController.class.getName());

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto) throws InterruptedException {
        log.info("Accepted the request with thread: "+ Thread.currentThread().getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.createEmployeeSynchronousWay(employeeDto));
    }

    @PostMapping("async/")
    public ResponseEntity createEmployeeAsync(@RequestBody EmployeeDto employeeDto) throws InterruptedException {
        log.info("Accepted the request with thread: "+ Thread.currentThread().getName());
        long employeeId = employeeService.createEmployee(employeeDto);
        employeeService.employeePFCalculation(employeeId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Your request may take 8 seconds. Pease check the response at http://localhost:8080/v1/employees/"+ employeeId);
    }

    @GetMapping(value = "{id}")
    public EmployeeDto fetchEmployee(@PathVariable Long id) {
        return employeeService.fetchEmployee(id);
    }





}
