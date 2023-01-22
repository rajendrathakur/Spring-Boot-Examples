package com.springboot.examples.repository;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.model.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCity(String city);
}
