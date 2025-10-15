package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.LendingRequest;
import az.developia.librarian_jahangir_askerov.response.LendingResponse;
import az.developia.librarian_jahangir_askerov.service.LendingService;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/lending")
public class LendingController {

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private LendingService service;

	@PostMapping(path = "/borrow")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<LendingResponse> borrow(@Valid @RequestBody LendingRequest req, BindingResult br) {

		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		return new ResponseEntity<LendingResponse>(service.borrow(req), HttpStatus.CREATED);

	}

}
