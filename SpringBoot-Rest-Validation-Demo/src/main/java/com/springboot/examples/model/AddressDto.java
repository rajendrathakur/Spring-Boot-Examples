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
   @NotBlank(message = "houseNumber can not be blank")
    private String houseNumber;

    @NotBlank(message = "street can not be blank")
    private String street;

    @Size.List({
         @Size(min=5, message = "City must be atleast {min} characters"),
         @Size(max=9, message = "City must be less than {max} characters")
    })
    private String city;

    @PinCodeValidation
    private Integer pincode;
}
