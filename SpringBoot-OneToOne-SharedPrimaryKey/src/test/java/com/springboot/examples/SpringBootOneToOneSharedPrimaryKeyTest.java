package com.springboot.examples;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.entity.Laptop;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.repository.LaptopRepository;
import com.springboot.examples.utility.LazyTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SpringBootOneToOneSharedPrimaryKeyTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LazyTest lazyTest;

    @Test
    public void insertRecords() {
        Employee employee = new Employee("Rajendra", 75000);

        Laptop laptop = new Laptop("inspiron", "Lenovo");
        laptop.setEmployee(employee);

        laptopRepository.save(laptop);
    }

    @Test
    public void insertFreshRecords() {

        //newly Joined Employee
        Employee employee = new Employee("Manisha", 45000);
        employeeRepository.save(employee);

        //New Laptop
        Laptop laptop = new Laptop("Probook", "HP");
        laptop.setEmployee(null);
        laptopRepository.save(laptop);


    }

    @Test
    public void fetchLaptopRecord() {
        Laptop laptop = laptopRepository.findById(1l).get();
        assertEquals("Lenovo", laptop.getCompany());
        assertEquals("Rajendra", laptop.getEmployee().getName());
    }

    @Test
    public void fetchLaptopRecordByEmployeeId() {
        lazyTest.verifyLazyloading();
    }


    @Test
    public void deleteLaptop() {
        laptopRepository.deleteById(2l);
        Optional<Laptop> laptopOptional = laptopRepository.findById(2l);
        Optional<Employee> employeeOptional = employeeRepository.findById(2l);
        assertFalse(laptopOptional.isPresent());
        assertFalse(employeeOptional.isPresent());

    }


}