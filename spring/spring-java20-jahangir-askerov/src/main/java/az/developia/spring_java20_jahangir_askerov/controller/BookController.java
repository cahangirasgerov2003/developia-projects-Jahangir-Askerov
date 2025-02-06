package az.developia.spring_java20_jahangir_askerov.controller;

import az.developia.spring_java20_jahangir_askerov.model.Book;
import az.developia.spring_java20_jahangir_askerov.model.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Inversion of controller
@RequestMapping(path = "/books")
public class BookController {
	@Autowired // Dependency injection
	private BookService service;

	@GetMapping(path = "/all")
	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping(path = "/{id}")
	public Book getBookById(@PathVariable Integer id) {
		return service.getBookById(id);
	}

	@GetMapping(path = "/search")
	public List<Book> getBooksByName(@RequestParam(name = "name") String query) {
		return service.getBooksByName(query);
	}

	@PostMapping(path = "/create")
	public Integer createNewBook(@RequestBody Book book) {
		return service.createNewBook(book);
	}

	@PutMapping(path = "/{id}")
	public void updateBookById(@PathVariable Integer id, @RequestBody BookUpdateRequest book) {
		service.updateBookById(id, book);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteBookById(@PathVariable Integer id) {
		service.deleteBookById(id);
	}

}
