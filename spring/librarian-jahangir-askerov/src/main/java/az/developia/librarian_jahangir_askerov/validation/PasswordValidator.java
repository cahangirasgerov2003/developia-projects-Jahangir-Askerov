package az.developia.librarian_jahangir_askerov.validation;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (password == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{cannot.be.empty}").addConstraintViolation();
			return false;
		}

		boolean valid = true;

		if (!password.matches(".*[a-z].*")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password must contain at least one lowercase letter")
					.addConstraintViolation();
			valid = false;
		}

		if (!password.matches(".*[A-Z].*")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password must contain at least one uppercase letter")
					.addConstraintViolation();
			valid = false;
		}

		if (!password.matches(".*\\d.*")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password must contain at least one digit")
					.addConstraintViolation();
			valid = false;
		}

		if (!password.matches(".*[@$!%*?&()_+=^,.;:/<>|\\-].*")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password must contain at least one special character")
					.addConstraintViolation();
			valid = false;
		}

		if (password.length() < 8 || password.length() > 20) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password must be between 8 and 20 characters long")
					.addConstraintViolation();
			valid = false;
		}

		return valid;
	}

}
