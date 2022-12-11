package com.springboot.examples;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.entity.Laptop;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.repository.LaptopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SpringBootOneToOneBiDirectionalMappingTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Test
    public void insertRecords() {
        Employee employee = new Employee("Rajendra", 75000);

        Laptop laptop = new Laptop("inspiron", "Lenovo");
        laptop.setEmployee(employee);

        laptopRepository.save(laptop);
    }

    @Test
    public void insertEmployeeRecords() {
        Employee employee = new Employee("Ajay", 85000);

        Laptop laptop = new Laptop("Vaio", "Sony");
        laptop.setEmployee(employee);
        employee.setLaptop(laptop);;

        employeeRepository.save(employee);
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
    public void mergeFreshRecords() {
      Laptop laptop = laptopRepository.findById(3l).get();
      Employee employee = employeeRepository.findById(3l).get();
      laptop.setEmployee(employee);
      employee.setLaptop(laptop);
      employeeRepository.save(employee);
    }

    @Test
    public void fetchLaptopRecord() {
        Laptop laptop = laptopRepository.findById(1l).get();
        assertEquals("Lenovo", laptop.getCompany());
        assertEquals("Rajendra", laptop.getEmployee().getName());
    }

    @Test
    public void fetchEmployeeRecord() {
        Employee employee = employeeRepository.findById(1l).get();
        assertEquals("Rajendra", employee.getName());
        assertEquals("Lenovo", employee.getLaptop().getCompany());
    }

    @Test
    public void deleteLaptop() {
        laptopRepository.deleteById(2l);
        Optional<Laptop> laptopOptional = laptopRepository.findById(2l);
        Optional<Employee> employeeOptional = employeeRepository.findById(2l);
        assertFalse(laptopOptional.isPresent());
        assertFalse(employeeOptional.isPresent());
    }

    @Test
    public void deleteEmployee() {
        employeeRepository.deleteById(3l);
        Optional<Laptop> laptopOptional = laptopRepository.findById(3l);
        Optional<Employee> employeeOptional = employeeRepository.findById(3l);
        assertFalse(laptopOptional.isPresent());
        assertFalse(employeeOptional.isPresent());
    }

}