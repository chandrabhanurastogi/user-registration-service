package com.gamesys.user.registration.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    private long minAge;

    @Override
    public void initialize(Age ageValue) {
        this.minAge = ageValue.value();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(date, today) >= minAge;
    }
}
