package com.springboot.examples;

import com.springboot.examples.entity.Customer;
import com.springboot.examples.entity.Loan;
import com.springboot.examples.repository.CustomerRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SpringBootOneToManyUniDirectionalMappingParentToChildTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LazyTesting lazyTesting;


    @Test
    public void insertRecords() {
        //insert the data for customer - 1
        Customer customer1 = new Customer("Rajendra", 9515678123l);

        Loan personalLoan1 = new Loan(250000, LocalDate.parse("2015-02-20"));
        Loan homeLoan1 = new Loan(780000, LocalDate.parse("2018-05-24"));
        Loan carLoan1 = new Loan(980000, LocalDate.parse("2019-08-24"));
        customer1.getLoans().addAll(Arrays.asList(personalLoan1, homeLoan1, carLoan1));
        customerRepository.save(customer1);

        //insert the data for customer - 2
        Customer customer2 = new Customer("Nikitha", 7893215643l);

        Loan personalLoan2 = new Loan(275000, LocalDate.parse("2013-02-20"));
        Loan homeLoan2 = new Loan(480000, LocalDate.parse("2016-05-24") );
        customer2.getLoans().addAll(Arrays.asList(personalLoan2, homeLoan2));
        customerRepository.save(customer2);

    }

    @Test
    public void fetchCustomer() {
        lazyTesting.fetchCustomerData();
    }


    @Test
    public void deleteCustomer() {
        customerRepository.deleteById(1l);
        Optional<Customer> customer = customerRepository.findById(1L);
        assertFalse(customer.isPresent());
    }

    @Test
    public void deleteLoanByCustomerId() {
        lazyTesting.deleteLoanByCustomerId();
    }

}