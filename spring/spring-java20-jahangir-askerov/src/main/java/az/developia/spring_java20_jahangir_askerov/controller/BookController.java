package az.developia.spring_java20_jahangir_askerov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.service.Service;

@RestController // Inversion of controller
public class BookController {
	@Autowired // Dependency injection
	private Service service;

	@GetMapping("books")
	public List<String> getAllBooks() {
		return service.getAllBooks();
	}
}
