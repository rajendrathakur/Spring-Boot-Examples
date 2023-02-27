package com.springboot.examples.scheduler;

import com.springboot.examples.entity.Donor;
import com.springboot.examples.entity.Employee;
import com.springboot.examples.model.DonorDto;
import com.springboot.examples.model.EmployeeDto;
import com.springboot.examples.repository.DonorRepository;
import com.springboot.examples.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class SchedulerTaskDemo {

    private static final Logger LOGGER =
            Logger.getLogger(SchedulerTaskDemo.class.getName());

    private EmployeeRepository employeeRepository;
    private DonorRepository donorRepository;

    private ModelMapper modelMapper;

    @Scheduled(fixedRate=5000l)
    @Async
    public void createEmployee() throws InterruptedException{
        LOGGER.info("Employee Creation Started at "+ Thread.currentThread().getName());
        EmployeeDto employeeDto = new EmployeeDto("Rajendra", 34, 45000, "Hyderabad");
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        Thread.sleep(7000l);
        LOGGER.info("Employee Creation Ended at "+ Thread.currentThread().getName());
    }

    @Scheduled(fixedRate=5000l)
    //@Async("threadPoolTaskExecutor")
    @Async
    public void createDonor() throws InterruptedException{
        LOGGER.info("Donor Creation Started at "+ Thread.currentThread().getName());
        DonorDto donorDto = new DonorDto("Ashok", 34, 25000, "Hyderabad");
        Donor donor = modelMapper.map(donorDto, Donor.class);
        donorRepository.save(donor);
        Thread.sleep(7000l);
        LOGGER.info("Donor Creation Ended at "+ Thread.currentThread().getName());
    }


}
