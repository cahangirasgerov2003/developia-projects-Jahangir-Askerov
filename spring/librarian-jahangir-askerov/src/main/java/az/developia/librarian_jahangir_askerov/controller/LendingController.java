package az.developia.librarian_jahangir_askerov.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.BorrowedBookUpdateRequest;
import az.developia.librarian_jahangir_askerov.request.LendingRequest;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookDetailsListResponse;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookDetailsResponse;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookListResponse;
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

	@GetMapping(path = "/borrowed-books")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<BorrowedBookListResponse> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping(path = "/borrowed-books/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<BorrowedBookDetailsResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping(path = "/borrowed-books/details")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<BorrowedBookDetailsListResponse> getByBorrowDateBetween(
			@RequestParam(name = "startDate") LocalDate startDate, @RequestParam(name = "endDate") LocalDate endDate) {
		return ResponseEntity.ok(service.getByBorrowDateBetween(startDate, endDate));
	}

	@GetMapping(path = "/returned-books")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<BorrowedBookDetailsListResponse> getReturnedByBorrowDateBetween(
			@RequestParam(name = "startDate") LocalDate startDate, @RequestParam(name = "endDate") LocalDate endDate) {
		return ResponseEntity.ok(service.getReturnedByBorrowDateBetween(startDate, endDate, LendingStatus.RETURNED ));
	}

	@PutMapping(path = "/borrowed-books/{id}/return")
	@PreAuthorize(value = "hasAuthority('ROLE_LENDING_BOOK')")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @RequestBody BorrowedBookUpdateRequest req,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}
		service.updateById(id, req);
		return ResponseEntity.noContent().build();

	}
}
