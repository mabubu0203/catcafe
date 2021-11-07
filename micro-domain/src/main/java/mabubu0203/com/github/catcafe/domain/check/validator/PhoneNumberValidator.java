package mabubu0203.com.github.catcafe.domain.check.validator;

import mabubu0203.com.github.catcafe.domain.check.CheckPhoneNumber;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<CheckPhoneNumber, Object> {

  @Override
  public void initialize(CheckPhoneNumber constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    return true;
  }

}
