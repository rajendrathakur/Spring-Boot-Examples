package com.springboot.examples.model;

import com.springboot.examples.validation.PinCodeValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInfo {
    private int id;

    @NotBlank(message = "houseNumber can not be blank")
    private String houseNumber;

    @NotBlank(message = "street can not be blank")
    private String street;

    @NotBlank(message = "city can not be blank")
    @Size(min = 5, max = 6, message = "city name must be between 5 & 6 letters")
    private String city;

    @PinCodeValidation
    private Integer pincode;
}
