package com.springboot.examples.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DonorDto {
    private long id;
    private final String name;
    private final int age;
    private final int donation;
    private final String city;
}
