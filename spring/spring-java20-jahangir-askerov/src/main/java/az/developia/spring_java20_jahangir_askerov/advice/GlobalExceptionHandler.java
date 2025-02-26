package az.developia.spring_java20_jahangir_askerov.advice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.developia.spring_java20_jahangir_askerov.exception.MyException;
import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.exception.UserAlreadyExistsException;
import az.developia.spring_java20_jahangir_askerov.exception.ValidationException;
import az.developia.spring_java20_jahangir_askerov.model.ValidationFieldError;
import az.developia.spring_java20_jahangir_askerov.response.AuthorizationDeniedExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.response.MyExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.response.NotFoundExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.response.UserAlreadyExistsExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.response.ValidationExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ValidationExceptionResponse handleException(ValidationException e) {
		BindingResult br = e.getBr();
		List<FieldError> fieldErrors = br.getFieldErrors();
		List<ValidationFieldError> errs = new ArrayList<ValidationFieldError>();
		for (FieldError fieldError : fieldErrors) {
			ValidationFieldError err = new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage());
			errs.add(err);
		}

		ValidationExceptionResponse resp = new ValidationExceptionResponse();
		resp.setErrorOccurrenceTime(LocalDate.now());
		resp.setMessage(e.getMessage());
		resp.setFieldErrors(errs);
		return resp;
	}

	@ExceptionHandler(NotFoundException.class)
	public NotFoundExceptionResponse handleException(NotFoundException e) {
		NotFoundExceptionResponse resp = new NotFoundExceptionResponse();
		resp.setMessage(e.getMessage());
		resp.setErrorOccurrenceTime(LocalDate.now());
		return resp;
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public UserAlreadyExistsExceptionResponse handleException(UserAlreadyExistsException e) {
		UserAlreadyExistsExceptionResponse resp = new UserAlreadyExistsExceptionResponse();
		resp.setErrorOccurrenceTime(LocalDate.now());
		resp.setMessage(e.getMessage());
		resp.setUsername(e.getUsername());
		return resp;
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public AuthorizationDeniedExceptionResponse handleException(AuthorizationDeniedException e) {
		AuthorizationDeniedExceptionResponse resp = new AuthorizationDeniedExceptionResponse();
		resp.setErrorOccurrenceTime(LocalDate.now());
		resp.setMessage(e.getMessage());
		return resp;
	}

	@ExceptionHandler(MyException.class)
	public MyExceptionResponse handleException(MyException e) {
		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setErrorOccurrenceTime(LocalDate.now());
		resp.setMessage(e.getMessage());
		return resp;
	}

}
