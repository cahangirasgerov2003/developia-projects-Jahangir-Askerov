package az.developia.spring_java20_jahangir_askerov.service;

import az.developia.spring_java20_jahangir_askerov.model.Book;
import az.developia.spring_java20_jahangir_askerov.model.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookService {
	@Autowired
	private BookRepository repository;

	public List<Book> getAllBooks() {
		return repository.findAll();
	}

	public Integer createNewBook(Book book) {
		repository.save(book);
		return book.getId();
	}

	public Book getBookById(Integer id) {
		Optional<Book> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			return null;
		}

		return optionalBook.get();
	}

	public void deleteBookById(Integer id) {
		repository.deleteById(id);
	}

	public void updateBookById(Integer id, BookUpdateRequest book) {
		Optional<Book> optionalBook = repository.findById(id);
		double price = book.getPrice();
		if (optionalBook.isEmpty()) {
			return;
		}
 
		Book existingBook = optionalBook.get();
		existingBook.setPrice(price);
		repository.save(existingBook);
	}

	public List<Book> getBooksByName(String query) {
		return repository.findAllByNameContaining(query);
	}
}
