package az.developia.spring_java20_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.model.Book;
import az.developia.spring_java20_jahangir_askerov.repository.Repository;

@RestController
public class Service {
	@Autowired
	private Repository repository;

	public List<Book> getAllBooks() {
		return repository.getAllBooks();
	}

	public void createNewBook(Book book) {
		repository.createNewBook(book);
	}

	public List<Book> getBooksByName(String query) {
		List<Book> allBooks = repository.getAllBooks();
		List<Book> filteredBooks = new ArrayList<Book>();

		if (query == null || query.trim().isEmpty()) {
			return allBooks;
		}

		for (Book book : allBooks) {
			if (book.getBookName().toLowerCase().contains(query.toLowerCase())) {
				filteredBooks.add(book);
			}
		}

		return filteredBooks;
	}
}
