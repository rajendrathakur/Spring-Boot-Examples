package com.springboot.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.examples.controller.EmployeeController;
import com.springboot.examples.exception.ResourceNotFoundException;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void createEmployeeWithValidData_thenReturn201() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        when(employeeService.createEmployee(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(post("/v1/employees/").contentType("application/json")
                .content(objectMapper.writeValueAsString(employeeDto))
                .header("User-Agent", "chrome"))
                .andExpect(status().isCreated());
    }

    @Test
    void createEmployeeWithInvalidData_thenReturn400() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 42, 45000, "Noida");
        mockMvc.perform(post("/v1/employees/").contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto))
                        .header("User-Agent", "chrome"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateEmployeeWithValidData_thenReturn200() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        when(employeeService.updateEmployee(employeeDto, 2l)).thenReturn(employeeDto);
        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeWithInvalidData_thenReturn400() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 42, 45000, "Noida");
        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateEmployee_EmployeeNotFound_thenReturn404() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        when(employeeService.updateEmployee(employeeDto, 2l)).thenThrow(new ResourceNotFoundException(2l));
        mockMvc.perform(put("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void fetchEmployee_thenReturn200() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        when(employeeService.fetchEmployee(2l)).thenReturn((employeeDto));
        mockMvc.perform(get("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(jsonPath("$.name").value("Rajendra"))
                .andExpect(jsonPath("$.city").value("Noida"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchEmployee_EmployeeNotFoundthenReturn404() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        when(employeeService.fetchEmployee(2l)).thenThrow((new ResourceNotFoundException(2l)));
        mockMvc.perform(get("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployee_thenReturn204() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        doNothing().when(employeeService).deleteEmployee(2l);
        mockMvc.perform(delete("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteEmployee_EmployeeNotFound_thenReturn404() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 35, 45000, "Noida");
        doThrow(new ResourceNotFoundException(2l)).when(employeeService).deleteEmployee(2l);
        mockMvc.perform(delete("/v1/employees/{id}", 2l).contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isNotFound());
    }


}
