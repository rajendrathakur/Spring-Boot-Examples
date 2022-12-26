package com.springboot.examples;

import com.springboot.examples.repository.ManufacturerRepository;
import com.springboot.examples.repository.ModelRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootManyToManyBiDirectionalMappingTest {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private LazyTesting lazyTesting;

   /* @Test
    public void insertManufacturerRecords() {
        lazyTesting.insertManufacturerRecords();
    }*/


    @Test
    public void insertModelRecords() {
        lazyTesting.insertModelRecords();
    }
    @Test
    public void insertManufacturerRecords_bidirectional() {
        lazyTesting.insertManufacturerRecordsBidirectional();
    }

    @Test
    public void insertManufacturerRecords_bidirectional_MappedBy() {
        lazyTesting.insertManufacturerRecordsBidirectional();
    }

    @Test
    public void deleteModelRecord_Without_Cascade() {
        modelRepository.deleteById(1l);
    }

    @Test
    public void deleteManufacturerRecord_Without_Cascade() {
        //manufacturerRepository.deleteById(2l);
        lazyTesting.deleteManufacturerRecordWithoutCascade();
    }

    @Test
    public void deleteManufacturerRecord_With_Cascade() {
        //manufacturerRepository.deleteById(2l);
        manufacturerRepository.deleteById(1l);
    }

    @Test
    public void deleteManufacturerRecord_BetterWay() {
        lazyTesting.deleteManufacturerRecordBetterWay();
    }

    @Test
    public void deleteModelRecord_BetterWay() {
        lazyTesting.deleteModelRecordBetterWay();
    }

}
