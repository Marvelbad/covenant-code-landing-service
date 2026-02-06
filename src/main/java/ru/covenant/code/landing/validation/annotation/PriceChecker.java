package ru.covenant.code.landing.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.covenant.code.landing.validation.PriceCheckerValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriceCheckerValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceChecker {

    String message() default "Цена должна быть выше 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
