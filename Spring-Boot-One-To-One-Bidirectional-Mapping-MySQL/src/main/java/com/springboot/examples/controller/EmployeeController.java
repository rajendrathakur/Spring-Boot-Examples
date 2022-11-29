package com.springboot.examples.controller;

import com.springboot.examples.domain.EmployeeInfo;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employees/")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeInfo> createEmployee(@RequestBody EmployeeInfo employeeInfo){
        return new ResponseEntity<>(employeeService.createEmployee(employeeInfo), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeInfo> fetchEmployee(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.fetchEmployee(id), HttpStatus.OK);
    }

}
