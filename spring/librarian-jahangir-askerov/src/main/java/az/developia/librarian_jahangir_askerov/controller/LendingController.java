package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.BookBorrowRequest;
import az.developia.librarian_jahangir_askerov.response.BookBorrowResponse;
import az.developia.librarian_jahangir_askerov.response.StudentBorrowSingleResponse;
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
	public ResponseEntity<BookBorrowResponse> borrow(@Valid @RequestBody BookBorrowRequest req, BindingResult br) {

		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		return new ResponseEntity<BookBorrowResponse>(service.borrow(req), HttpStatus.CREATED);

	}

	@GetMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<StudentBorrowSingleResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}

}
