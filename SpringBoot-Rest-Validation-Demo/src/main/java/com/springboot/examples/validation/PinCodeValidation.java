package com.springboot.examples.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {PinCodeValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PinCodeValidation {

    String message() default "pincode should be either 500070 or 500090";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
