package az.developia.spring_java20_jahangir_askerov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import az.developia.spring_java20_jahangir_askerov.model.BookEntity;
import az.developia.spring_java20_jahangir_askerov.model.BookUpdateRequest;
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

}
