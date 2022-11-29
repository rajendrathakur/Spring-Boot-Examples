package com.springboot.examples;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.entity.Laptop;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.repository.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootOneToOneBiDirectionalMappingH2 implements CommandLineRunner {

    private EmployeeRepository employeeRepository;
    private LaptopRepository laptopRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOneToOneBiDirectionalMappingH2.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Laptop laptop = new Laptop("Inspire", "Dell");
        Employee employee = new Employee("Kalpana", 75000);
        laptop.setEmployee(employee);
        employee.setLaptop(laptop);
        laptopRepository.save(laptop);
    }
}