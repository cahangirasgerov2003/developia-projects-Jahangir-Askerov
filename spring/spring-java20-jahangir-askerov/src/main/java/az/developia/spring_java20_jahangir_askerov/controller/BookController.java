package az.developia.spring_java20_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.ValidationException;
import az.developia.spring_java20_jahangir_askerov.request.BookAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.BookListResponse;
import az.developia.spring_java20_jahangir_askerov.response.BookSingleResponse;
import az.developia.spring_java20_jahangir_askerov.service.BookService;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController // Inversion of controller
@RequestMapping(path = "/books")
public class BookController {
	@Autowired // Dependency injection
	private BookService service;

	@Autowired
	private FileContentReader contentReader;

	@GetMapping(path = "/all")
	@PreAuthorize(value = "hasAuhority('ROLE_GET_BOOKS')")
	public BookListResponse getAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping(path = "/{id}")
	public BookSingleResponse getBookById(@PathVariable Integer id) {
		return service.getBookById(id);
	}

	@GetMapping(path = "/search")
	public BookListResponse getBooksByName(@RequestParam(name = "name", defaultValue = "") String query) {
		return service.getBooksByName(query);
	}

	@PostMapping(path = "/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize(value = "hasAuthority('ROLE_SET_BOOK')")
	public Integer createNewBook(@Valid @RequestBody BookAddRequest book, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException(contentReader.readFromFile("validationMessage.txt"), br);
		}
		return service.createNewBook(book);
	}

	@PutMapping(path = "/{id}")
	public void updateBookById(@PathVariable Integer id, @Valid @RequestBody BookUpdateRequest book, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException(contentReader.readFromFile("validationMessage.txt"), br);
		}

		service.updateBookById(id, book);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteBookById(@PathVariable Integer id) {
		service.deleteBookById(id);
	}

}
