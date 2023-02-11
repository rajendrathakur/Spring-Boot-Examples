package com.springboot.examples.controller;

import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
@Api(value="Employee Controller", description = "Employee CRUD Operations !!!")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "Create Employee", notes = "This API is to create employee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee is created Successfully", response = EmployeeDto.class),
            @ApiResponse(code = 400, message = "Invalid employee data")
    })
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto, @RequestHeader("User-Agent") String userAgent) {
        System.out.println("User agent  " + userAgent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("User-Agent", userAgent)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Get Employee", notes = "This API is to fetch employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is fetched Successfully", response = EmployeeDto.class),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    public EmployeeDto fetchEmployee(@ApiParam(value = "This is employee id") @PathVariable Long id) {
        return employeeService.fetchEmployee(id);
    }

    @GetMapping("query")
    @ApiOperation(value = "Get Employee", notes = "This API is to fetch employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employees are fetched Successfully")
    })
    public List<EmployeeDto> fetchEmployees(@RequestParam(required = false) String city) {
        if (city != null) {
            return employeeService.fetchEmployeesByCity(city);
        } else {
            return employeeService.fetchEmployees();
        }
    }

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Update Employee", notes = "This API is to update employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is updated Successfully", response = EmployeeDto.class),
            @ApiResponse(code = 404, message = "Employee not found")
    })
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
