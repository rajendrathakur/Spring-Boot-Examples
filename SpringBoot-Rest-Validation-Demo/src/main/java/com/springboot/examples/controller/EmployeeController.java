package com.springboot.examples.controller;

import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.model.GroupOne;
import com.springboot.examples.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.bytebuddy.dynamic.ClassFileLocator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/v1/employees/")
@AllArgsConstructor
@Validated
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody  @Validated(Default.class) EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> fetchEmployee(@PathVariable  @Min(3) int id) {
        return ResponseEntity.ok(employeeService.fetchEmployee(id));
    }

}
