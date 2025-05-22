package com.example.transaction_service.service.validation.validator;

import com.example.transaction_service.service.validation.annotation.Letters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LettersValidator implements ConstraintValidator<Letters, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if(string == null){
            return false;
        }
        for(char c: string.toLowerCase().toCharArray()){
            if (c < 65 || (c > 90 && c < 97) || c > 122) {
                return false;
            }
        }
        return true;
    }
}
