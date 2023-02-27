package com.springboot.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.examples.controller.EmployeeController;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc  mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;


    @Test
    public void createEmployeeRecord_thenReturn201() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        when(employeeService.createEmployee(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(post("/v1/employees/").contentType("application/json")
                .content(objectMapper.writeValueAsString(employeeDto))
                .header("user-agent", "chrome"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createEmployeeRecord_thenReturn400() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 42, 45000, "Hyderabad");
        mockMvc.perform(post("/v1/employees/").contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto))
                        .header("user-agent", "chrome"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateEmployeeRecord_thenReturn200() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        when(employeeService.updateEmployee(employeeDto, 2l)).thenReturn(employeeDto);
        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto))
                        )
                .andExpect(status().isOk());
    }

    @Test
    public void updateEmployeeRecord_thenReturn400() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 42, 45000, "Hyderabad");

        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateEmployeeRecord_thenReturn404() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        when(employeeService.updateEmployee(employeeDto, 2l)).thenThrow(new ResourceNotFoundException(2l));
        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void fetchEmployeeRecord_thenReturn200() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        when(employeeService.fetchEmployee(2l)).thenReturn(employeeDto);
        mockMvc.perform(get("/v1/employees/{id}", 2l))
                .andExpect(status().isOk());
    }

    @Test
    public void fetchEmployeeRecord_thenReturn404() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        when(employeeService.fetchEmployee(2l)).thenThrow(new ResourceNotFoundException(2l));
        mockMvc.perform(get("/v1/employees/{id}", 2l))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteEmployeeRecord_thenReturn204() throws Exception {
        doNothing().when(employeeService).deleteEmployee(2l);
        mockMvc.perform(delete("/v1/employees/{id}", 2l))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteEmployeeRecord_thenReturn404() throws Exception {
        doThrow(new ResourceNotFoundException(2l)).when(employeeService).deleteEmployee(2l);
        mockMvc.perform(delete("/v1/employees/{id}", 2l))
                .andExpect(status().isNotFound());
    }






}
