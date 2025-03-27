package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.BookAddRequest;
import az.developia.librarian_jahangir_askerov.request.BookUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.BookAddResponse;
import az.developia.librarian_jahangir_askerov.response.BookListResponse;
import az.developia.librarian_jahangir_askerov.response.BookSingleResponse;
import az.developia.librarian_jahangir_askerov.service.BookService;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/books")
public class BookController {
	@Autowired
	private BookService service;

	@Autowired
	private FileContentReader contentReader;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_BOOK')")
	public ResponseEntity<BookAddResponse> create(@Valid @RequestBody BookAddRequest req, BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationMessage.txt"), br, "ValidationException");
		}
		return new ResponseEntity<BookAddResponse>(service.create(req), HttpStatus.CREATED);
	}

	@GetMapping(path = "/all")
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_ALL_BOOKS')")
	public ResponseEntity<BookListResponse> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_BY_ID_BOOK')")
	public ResponseEntity<BookSingleResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping(path = "/search")
	@PreAuthorize(value = "hasAuthority('ROLE_SEARCH_BOOK')")
	public ResponseEntity<BookListResponse> getByName(@RequestParam(name = "name", defaultValue = "") String q) {
		return ResponseEntity.ok(service.getByName(q));
	}

	@GetMapping
	@PreAuthorize(value = "hasAuthority('ROLE_PAGINATE_BOOK')")
	public ResponseEntity<BookListResponse> getPaginated(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size) {
		return ResponseEntity.ok(service.getPaginated(page, size));
	}

	@PutMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_BOOK')")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @Valid @RequestBody BookUpdateRequest req,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationMessage.txt"), br, "ValidationException");
		}

		service.updateById(id, req);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_BOOK')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
