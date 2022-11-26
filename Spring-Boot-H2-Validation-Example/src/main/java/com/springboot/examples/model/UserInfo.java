package com.springboot.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private int id;

    @NotBlank(message = "name can not be blank")
    private String name;

    @Email(message = "email is not valid")
    private String email;

    //@Range(min = 10, max = 10, message = "phoneNumber must be 10 digits")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @Valid
    private AddressInfo addressInfo;
}
