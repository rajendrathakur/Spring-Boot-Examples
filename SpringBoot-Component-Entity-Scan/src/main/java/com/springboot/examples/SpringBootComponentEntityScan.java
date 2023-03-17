package com.springboot.examples;

import common.entity.scan.SpringBootEntityScanDependency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//Solution - 1
/*@EntityScan("common.entity.scan.entity")
@EnableJpaRepositories("common.entity.scan.repository")*/

//Solution - 2
//@ComponentScan("com.springboot.examples, common.entity.scan")

//Solution - 3
@Import(SpringBootEntityScanDependency.class)
public class SpringBootComponentEntityScan {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootComponentEntityScan.class, args);
    }

}