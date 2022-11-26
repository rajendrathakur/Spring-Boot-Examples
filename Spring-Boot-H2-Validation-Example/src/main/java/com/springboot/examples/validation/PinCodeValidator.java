package com.springboot.examples.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PinCodeValidator implements ConstraintValidator<PinCodeValidation, Integer>
{
    @Override
    public boolean isValid(Integer pincode, ConstraintValidatorContext constraintValidatorContext) {
        return pincode == 500070 || pincode == 500090;
    }

    @Override
    public void initialize(PinCodeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
