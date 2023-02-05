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
public class AddressDto {

    private int id;

    @NotBlank(message = "housenumber can't be blank")
    private String houseNumber;

    @NotBlank(message = "Street can't be blank")
    private String street;

   @Size(max = 8, message = "City name must not have moer than {max} characters")
    private String city;

    @PinCodeValidation
    private Integer pincode;
}
