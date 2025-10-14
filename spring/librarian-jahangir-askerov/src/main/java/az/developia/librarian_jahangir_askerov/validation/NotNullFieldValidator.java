package az.developia.librarian_jahangir_askerov.validation;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class NotNullFieldValidator implements ConstraintValidator<NotNullField, Object> {

	private String fieldName;

	@Override
	public void initialize(NotNullField constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value != null)
			return true;

		context.disableDefaultConstraintViolation();

		context.buildConstraintViolationWithTemplate(fieldName.toString() + " cannot be empty.")
				.addConstraintViolation();

		return false;
	}

}
