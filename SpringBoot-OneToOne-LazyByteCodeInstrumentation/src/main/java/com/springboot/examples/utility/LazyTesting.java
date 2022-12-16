package com.springboot.examples.utility;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.entity.Laptop;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class LazyTesting {
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional
    public void fetchLaptop() {
        Laptop laptop = laptopRepository.findById(1l).get();
        assertEquals("Lenovo", laptop.getCompany());
        assertEquals("Rajendra", laptop.getEmployee().getName());
    }

    @Transactional
    public void fetchEmployee() {
        Employee employee = employeeRepository.findById(1l).get();
        assertEquals("Ajay", employee.getName());
        assertEquals("Sony", employee.getLaptop().getCompany());
    }

}
