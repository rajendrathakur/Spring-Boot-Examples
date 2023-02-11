package com.springboot.examples.controller;

import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees/")
@Tag(value = "My API")
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(summary = "Post API", description = "This is to create employee", tags= "Post")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Employee is created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Employee data",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto, @RequestHeader("User-Agent") String userAgent) {
        System.out.println("User agent  " + userAgent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("User-Agent", userAgent)
                .body(employeeService.createEmployee(employeeDto));
    }

    @Operation(summary = "Get API", description = "This is to fetch employee", tags= "Get")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Employee is fetched successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @GetMapping(value = "{id}")
    public EmployeeDto fetchEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id) {
        return employeeService.fetchEmployee(id);
    }

    @Operation(summary = "Get API", description = "This is to fetch employees", tags= "Get")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Employees are fetched successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))),
            @ApiResponse(responseCode = "404", description = "Employees are not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @GetMapping("query")
    public List<EmployeeDto> fetchEmployees(@RequestParam(required = false) String city) {
        if (city != null) {
            return employeeService.fetchEmployeesByCity(city);
        } else {
            return employeeService.fetchEmployees();
        }

    }

    @Operation(summary = "Update API", description = "This is to update employees", tags= "Put")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Employee is updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee is not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @PutMapping(value = "{id}")
    public EmployeeDto updateEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

    @Operation(summary = "Delete API", description = "This is to delete employee", tags= "Delete")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "204", description = "Employee is deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee is not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Parameter(description = "This is Employee Id") @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @Operation(summary = "Delete API", description = "This is to delete employees", tags= "Delete")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "204", description = "Employees are deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee is not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema))
    })
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployees() {
        employeeService.deleteEmployees();
    }




}
