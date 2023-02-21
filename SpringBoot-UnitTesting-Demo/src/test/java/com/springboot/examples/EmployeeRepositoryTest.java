package com.springboot.examples;

import com.springboot.examples.entity.Employee;
import com.springboot.examples.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void fetchEmployeeRecords_norecords() {
        List<Employee> employeeList = employeeRepository.findAll();
        assert(employeeList).isEmpty();
    }

    @Test
    public void fetchEmployeeRecords_allrecords() {
        Employee employee1 = new Employee("Ashok", 23,34000, "Hyderabad");
        entityManager.persist(employee1);

        Employee employee2 = new Employee("Mohan", 28,44000, "Hyderabad");
        entityManager.persist(employee2);

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(2).contains(employee1, employee2);
    }

    @Test
    public void fetchEmployeeRecords_ByCity() {
        Employee employee1 = new Employee("Ashok", 23,34000, "Hyderabad");
        entityManager.persist(employee1);

        Employee employee2 = new Employee("Mohan", 28,44000, "Hyderabad");
        entityManager.persist(employee2);

        Employee employee3 = new Employee("Raja", 26,34000, "Delhi");
        entityManager.persist(employee3);

        List<Employee> employeeList = employeeRepository.findByCity("Hyderabad");
        assertThat(employeeList).hasSize(2).contains(employee1, employee2);
    }


    @Test
    public void createEmployeeRecords() {
        Employee employee = employeeRepository.save(new Employee("Ashok", 23,34000, "Hyderabad"));

        assertThat(employee).hasFieldOrPropertyWithValue("name", "Ashok");
        assertThat(employee).hasFieldOrPropertyWithValue("age", 23);
        assertThat(employee).hasFieldOrPropertyWithValue("salary", 34000);
        assertThat(employee).hasFieldOrPropertyWithValue("city", "Hyderabad");
    }

    @Test
    public void fetchEmployeeById() {
        Employee employee1 = new Employee("Ashok", 23,34000, "Hyderabad");
        entityManager.persist(employee1);
        Employee foundEmployee = employeeRepository.findById(employee1.getId()).get();
        assertThat(foundEmployee).isEqualTo(employee1);
    }

}
