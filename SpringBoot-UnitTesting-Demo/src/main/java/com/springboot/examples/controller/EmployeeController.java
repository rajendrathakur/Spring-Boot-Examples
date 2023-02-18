package com.springboot.examples.controller;

import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
//@Tag(name = "Emp API", description = "Employee API's")
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(summary = "Post API", description = "This si to ceate employee", tags = "Post",
    responses = {
            @ApiResponse(responseCode = "201", description = "Employee is created sucessfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Employee Data")
    })
    @PostMapping
    public ResponseEntity createEmployee(@Valid  @RequestBody EmployeeDto employeeDto, @RequestHeader("User-Agent") String userAgent) {
        System.out.println("User agent  " + userAgent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("User-Agent", userAgent)
                .body(employeeService.createEmployee(employeeDto));
    }

    @Operation(summary = "Get API", description = "This si to fetech employee", tags = "Get",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee is fetched sucessfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDto.class))),
                    @ApiResponse(responseCode = "404", description = "Employee record not found")
            })
    @GetMapping(value = "{id}")
    public EmployeeDto fetchEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id) {
        return employeeService.fetchEmployee(id);
    }


    @Operation(summary = "Get API", description = "This si to fetech employees", tags = "Get",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employees are fetched sucessfully",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))),
                    @ApiResponse(responseCode = "404", description = "Employee record not found")
            })
    @GetMapping("query")
    public List<EmployeeDto> fetchEmployees(@RequestParam(required = false) String city) {
        if (city != null) {
            return employeeService.fetchEmployeesByCity(city);
        } else {
            return employeeService.fetchEmployees();
        }

    }

    @Operation(summary = "Put API", description = "This si to update employee", tags = "Put",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee is updated sucessfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Employee Data"),
                    @ApiResponse(responseCode = "404", description = "Employee record not found")
            })
    @PutMapping(value = "{id}")
    public EmployeeDto updateEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id, @Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }


    @Operation(summary = "Delete API", description = "This si to delete employee", tags = "Delete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Employee is deleted sucessfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Employee record not found")
            })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }


    @Operation(summary = "Delete API", description = "This si to delete employees", tags = "Delete",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Employees are deleted sucessfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Employee record not found")
            })
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployees() {
        employeeService.deleteEmployees();
    }




}
