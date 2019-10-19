package com.gamesys.user.registration.validator;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, char[]> {
   public void initialize(ValidPassword constraint) {
   }

   public boolean isValid(char[] password, ConstraintValidatorContext context) {
      PasswordValidator validator = new PasswordValidator(Arrays.asList(
              new LengthRule(4, 30),
              new UppercaseCharacterRule(1),
              new DigitCharacterRule(1),
              new WhitespaceRule()));

      RuleResult result = validator.validate(new PasswordData(String.valueOf(password)));
      if (result.isValid()) {
         return true;
      }
      context.disableDefaultConstraintViolation();

//      context.buildConstraintViolationWithTemplate(
//              Joiner.on(",").join(validator.getMessages(result)))
//              .addConstraintViolation();
//
      context.buildConstraintViolationWithTemplate(
              String.join(",", validator.getMessages(result)))
              .addConstraintViolation();

      return false;
   }
}
