package az.developia.spring_java20_jahangir_askerov.advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.exception.ValidationException;
import az.developia.spring_java20_jahangir_askerov.model.ValidationFieldError;
import az.developia.spring_java20_jahangir_askerov.response.NotFoundExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.response.ValidationExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ValidationExceptionResponse handleValidationException(ValidationException e) {
		BindingResult bindingResult = e.getBr();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ValidationFieldError> validationFieldErrors = new ArrayList<ValidationFieldError>();
		for (FieldError fieldError : fieldErrors) {
			ValidationFieldError validationFieldError = new ValidationFieldError(fieldError.getField(),
					fieldError.getDefaultMessage());
			validationFieldErrors.add(validationFieldError);
		}

		ValidationExceptionResponse response = new ValidationExceptionResponse();
		response.setErrorOccurrenceTime(LocalDateTime.now());
		response.setMessage(e.getMessage());
		response.setFieldErrors(validationFieldErrors);
		return response;
	}

	@ExceptionHandler(NotFoundException.class)
	public NotFoundExceptionResponse handleNotFoundException(NotFoundException e) {
		NotFoundExceptionResponse response = new NotFoundExceptionResponse();
		response.setMessage(e.getMessage());
		response.setErrorOccurrenceTime(LocalDateTime.now());
		return response;
	}
}
