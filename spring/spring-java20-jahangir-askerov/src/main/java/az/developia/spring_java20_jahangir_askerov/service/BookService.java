package az.developia.spring_java20_jahangir_askerov.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.model.BookEntity;
import az.developia.spring_java20_jahangir_askerov.model.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.repository.BookRepository;

@RestController
public class BookService {
	@Autowired
	private BookRepository repository;

	public List<BookEntity> getAllBooks() {
		return repository.findAll();
	}

	public Integer createNewBook(BookEntity bookEntity) {
		repository.save(bookEntity);
		return bookEntity.getId();
	}

	public BookEntity getBookById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException("The book with the ID you are looking for does not exist !");
		}

		return optionalBook.get();
	}

	public void deleteBookById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if(optionalBook.isEmpty()) {
			throw new NotFoundException("The book with the ID you are looking for does not exist !");
		}
		
		repository.deleteById(id);
	}

	public void updateBookById(Integer id, BookUpdateRequest book) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException("The book with the ID you are looking for does not exist !");
		}

		BigDecimal price = book.getPrice();
		String description = book.getDescription();
		
		BookEntity existingBook = optionalBook.get();
		existingBook.setPrice(price);
		existingBook.setDescription(description);
		repository.save(existingBook);
	}

	public List<BookEntity> getBooksByName(String query) {
		return repository.findAllByNameContaining(query);
	}
}
