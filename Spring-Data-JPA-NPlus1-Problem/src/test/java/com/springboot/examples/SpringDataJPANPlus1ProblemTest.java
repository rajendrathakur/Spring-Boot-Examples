package com.springboot.examples;

import com.springboot.examples.repository.DepartmentRepository;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringDataJPANPlus1ProblemTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LazyTesting lazyTesting;

    @Test
    public void insertAllRecords() {
        lazyTesting.insertAllRecords();
    }

    @Test
    public void fetchDepartmentRecords() {
        lazyTesting.fetchDepartmentRecords();
    }

    @Test
    public void findWithJoinFetch() {
        lazyTesting.findWithJoinFetch();
    }

    @Test
    public void findWithEntityGraph() {
        lazyTesting.fetchAllRecords();
    }

    @Test
    public void fetchProjects() {
        lazyTesting.fetchprojects();
    }

    @Test
    public void fetchEmployees() {
        lazyTesting.fetchEmployees();
    }

    @Test
    public void fetchAllRecords() {
        lazyTesting.fetchAllRecords();
    }

}
