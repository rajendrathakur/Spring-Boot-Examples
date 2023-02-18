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
@Api(value = "Employee API", description = "Employee CRUD operations")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "Post API", notes = "This is to create employee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee is created successfully"),
            @ApiResponse(code=400, message = "Invalid employee data")
    })
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto, @RequestHeader("User-Agent") String userAgent) {
        System.out.println("User agent  " + userAgent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("User-Agent", userAgent)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Get API", notes = "This is to fetch employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is fetched successfully"),
            @ApiResponse(code=404, message = "Employee record not found")
    })
    public EmployeeDto fetchEmployee(@ApiParam(name="id", value = "Employee Id") @PathVariable Long id) {
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
    @ApiOperation(value = "Put API", notes = "This is to update employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee is updated successfully"),
            @ApiResponse(code=400, message = "Invalid employee data"),
            @ApiResponse(code=404, message = "Employee record not found")
    })
    public EmployeeDto updateEmployee(@ApiParam(name = "Id", value = "Employee Id") @PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Delete API", notes = "This is to delete employee")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Employee is deleted successfully"),
            @ApiResponse(code=404, message = "Employee record not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@ApiParam(name = "id", value = "Employee Id") @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployees() {
        employeeService.deleteEmployees();
    }


}
