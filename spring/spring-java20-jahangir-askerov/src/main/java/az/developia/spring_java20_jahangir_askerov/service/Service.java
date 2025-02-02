package az.developia.spring_java20_jahangir_askerov.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.repository.Repository;

@RestController
public class Service {
	@Autowired
	private Repository repository;

	public List<String> getAllBooks() {
		return repository.getAllBooks();
	}
}
