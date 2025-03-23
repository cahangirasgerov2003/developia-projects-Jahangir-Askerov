package az.developia.librarian_jahangir_askerov.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, LocalDate> {

	public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {

		if (birthday == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{cannot.be.empty}").addConstraintViolation();
			return false;
		}

		boolean valid = true;

		LocalDate today = LocalDate.now();

		Period period = Period.between(birthday, today);

		if (period.getYears() < 18) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{librarian.age.restriction}").addConstraintViolation();
			valid = false;
		}

		return valid;
	}

}
