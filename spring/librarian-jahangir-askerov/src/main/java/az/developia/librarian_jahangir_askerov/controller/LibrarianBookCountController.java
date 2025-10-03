package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.response.LibrarianBookCountListResponse;
import az.developia.librarian_jahangir_askerov.service.LibrarianBookCountService;

@RestController
@RequestMapping(path = "librarians/book-count")
public class LibrarianBookCountController {

	@Autowired
	private LibrarianBookCountService service;

	@GetMapping
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_ALL_LIBRARIANS_WITH_BOOK_COUNT')")
	public ResponseEntity<LibrarianBookCountListResponse> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

}
