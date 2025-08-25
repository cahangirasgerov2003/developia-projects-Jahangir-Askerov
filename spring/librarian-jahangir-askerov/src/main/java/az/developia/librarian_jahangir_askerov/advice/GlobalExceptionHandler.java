package az.developia.librarian_jahangir_askerov.advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.model.ValidationFieldError;
import az.developia.librarian_jahangir_askerov.response.MyExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MyException.class)
	public MyExceptionResponse handleException(MyException e) {
		BindingResult br = e.getBr();
		List<ValidationFieldError> errs = new ArrayList<ValidationFieldError>();
		if (br != null) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			for (FieldError err : fieldErrors) {
				ValidationFieldError error = new ValidationFieldError(err.getField(), err.getDefaultMessage());
				errs.add(error);
			}
		}

		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setFieldErrors(errs);
		resp.setMessage(e.getMessage());
		resp.setType(e.getType());
		resp.setErrorOccurrenceTime(LocalDateTime.now());
		return resp;
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public MyExceptionResponse handleAuthorizationDenied(AuthorizationDeniedException e) {
		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setType("AuthorizationDeniedException");
		resp.setErrorOccurrenceTime(LocalDateTime.now());
		resp.setMessage(e.getMessage());
		return resp;
	}
}
