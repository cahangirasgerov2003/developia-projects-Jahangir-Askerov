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
import az.developia.spring_java20_jahangir_askerov.model.NotFoundExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.model.SellerEntity;
import az.developia.spring_java20_jahangir_askerov.model.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.model.ValidationExceptionResponse;
import az.developia.spring_java20_jahangir_askerov.model.ValidationFieldError;
import az.developia.spring_java20_jahangir_askerov.service.SellerService;
import jakarta.validation.Valid;

@RestController // Inversion of control
@RequestMapping(path = "sellers")
public class SellerController {
	@Autowired // Dependency injection
	private SellerService service;

	@GetMapping(path = "/all")
	public List<SellerEntity> getAllSellers() {
		return service.getAllSellers();
	}

	@GetMapping(path = "/{id}")
	public SellerEntity getSellerByID(@PathVariable Integer id) {
		return service.getSellerByID(id);
	}

	@GetMapping(path = "/search")
	public List<SellerEntity> getSellersByName(@RequestParam(name = "name", defaultValue = "") String query) {
		return service.getSellersByName(query);
	}

	@PostMapping(path = "/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Integer createNewSeller(@Valid @RequestBody SellerEntity seller, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException("There is a validation problem in the seller data !", br);
		}
		return service.createNewSeller(seller);
	}

	@PutMapping(path = "/{id}")
	public void updateSellerByID(@PathVariable Integer id, @Valid @RequestBody SellerUpdateRequest seller,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException("There is a validation problem in the seller data !", br);
		}
		service.updateSellerByID(id, seller);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteSellerByID(@PathVariable Integer id) {
		service.deleteSellerByID(id);
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
