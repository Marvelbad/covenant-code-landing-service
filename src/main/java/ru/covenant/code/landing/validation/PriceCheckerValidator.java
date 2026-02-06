package ru.covenant.code.landing.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.covenant.code.landing.validation.annotation.PriceChecker;

import java.math.BigDecimal;

public class PriceCheckerValidator implements ConstraintValidator<PriceChecker, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.compareTo(BigDecimal.ZERO) >= 0;
    }
}
