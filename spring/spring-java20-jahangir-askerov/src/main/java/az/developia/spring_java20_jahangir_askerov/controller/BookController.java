package az.developia.spring_java20_jahangir_askerov.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.exception.ValidationException;
import az.developia.spring_java20_jahangir_askerov.model.BookEntity;
import az.developia.spring_java20_jahangir_askerov.model.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.model.NotFoundExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.model.ValidationExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.model.ValidationFieldError;
import az.developia.spring_java20_jahangir_askerov.service.BookService;
import jakarta.validation.Valid;

@RestController // Inversion of controller
@RequestMapping(path = "/books")
public class BookController {
	@Autowired // Dependency injection
	private BookService service;

	@GetMapping(path = "/all")
	public List<BookEntity> getAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping(path = "/{id}")
	public BookEntity getBookById(@PathVariable Integer id) {
		return service.getBookById(id);
	}

	@GetMapping(path = "/search")
	public List<BookEntity> getBooksByName(@RequestParam(name = "name", defaultValue = "") String query) {
		return service.getBooksByName(query);
	}

	@PostMapping(path = "/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Integer createNewBook(@Valid @RequestBody BookEntity bookEntity, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException("There is a validation problem in the book data !", br);
		}
		return service.createNewBook(bookEntity);
	}

	@PutMapping(path = "/{id}")
	public void updateBookById(@PathVariable Integer id, @Valid @RequestBody BookUpdateRequest book, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException("There is a validation problem in the book data !", br);
		}

		service.updateBookById(id, book);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteBookById(@PathVariable Integer id) {
		service.deleteBookById(id);
	}

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
		return response;
	}

}
