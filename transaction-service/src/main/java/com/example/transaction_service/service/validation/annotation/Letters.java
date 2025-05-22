package com.example.transaction_service.service.validation.annotation;

import com.example.transaction_service.service.validation.validator.LettersValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LettersValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Letters {
    String message() default "string contains not only letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
