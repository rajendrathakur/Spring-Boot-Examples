package com.springboot.examples;

import com.springboot.examples.entity.Customer;
import com.springboot.examples.repository.CustomerRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SpringBootCascadeVsOrphanRemovalTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LazyTesting lazyTesting;


    @Test
    public void insertCustomerRecords() {
        lazyTesting.insertCustomerRecords();
    }

    @Test
    public void deleteCustomer() {
        customerRepository.deleteById(2l);
        Optional<Customer> customer = customerRepository.findById(2L);
        assertFalse(customer.isPresent());
    }

    @Test
    public void deleteLoanByCustomerId() {
        lazyTesting.deleteLoanByCustomerId();
    }

    @Test
    public void deleteAllLoanByCustomerId() { lazyTesting.deleteAllLoanByCustomerId(); }

}