package com.springboot.examples.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LaptopInfo {
    private long id;
    private String model;
    private String company;
}
