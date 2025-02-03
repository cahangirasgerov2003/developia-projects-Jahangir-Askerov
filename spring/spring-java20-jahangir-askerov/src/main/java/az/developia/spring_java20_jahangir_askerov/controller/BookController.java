package az.developia.spring_java20_jahangir_askerov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.model.Book;
import az.developia.spring_java20_jahangir_askerov.service.Service;

@RestController // Inversion of controller
@RequestMapping(path = "/books")
public class BookController {
	@Autowired // Dependency injection
	private Service service;

	@GetMapping(path = "/all")
	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}
}
