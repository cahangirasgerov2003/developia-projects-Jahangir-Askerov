package az.developia.spring_java20_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.model.BookEntity;
import az.developia.spring_java20_jahangir_askerov.repository.BookRepository;
import az.developia.spring_java20_jahangir_askerov.request.BookAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.BookUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.BookListResponse;
import az.developia.spring_java20_jahangir_askerov.response.BookSingleResponse;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@RestController
public class BookService {
	@Autowired
	private BookRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	public BookListResponse getAllBooks() {
		List<BookEntity> allBooks = repository.findAll();
		List<BookSingleResponse> customAllBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : allBooks) {
			BookSingleResponse bookSingleResponse = new BookSingleResponse();
			modelMapper.map(book, bookSingleResponse);
			customAllBooks.add(bookSingleResponse);
		}
		BookListResponse books = new BookListResponse();
		books.setBooks(customAllBooks);
		return books;
	}

	public Integer createNewBook(BookAddRequest book) {
		BookEntity bookEntity = new BookEntity();
		modelMapper.map(book, bookEntity);
		repository.save(bookEntity);
		return bookEntity.getId();
	}

	public BookSingleResponse getBookById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		BookSingleResponse customBook = new BookSingleResponse();
		BookEntity book = optionalBook.get();
		modelMapper.map(book, customBook);
		return customBook;
	}

	public void deleteBookById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		repository.deleteById(id);
	}

	public void updateBookById(Integer id, BookUpdateRequest book) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		BookEntity existingBook = optionalBook.get();

		modelMapper.map(book, existingBook);

		repository.save(existingBook);

	}

	public BookListResponse getBooksByName(String query) {
		List<BookEntity> searchedBooks = repository.findAllByNameContaining(query);
		List<BookSingleResponse> customSearchedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : searchedBooks) {
			BookSingleResponse bookSingleResponse = new BookSingleResponse();
			modelMapper.map(book, bookSingleResponse);
			customSearchedBooks.add(bookSingleResponse);
		}
		BookListResponse books = new BookListResponse();
		books.setBooks(customSearchedBooks);
		return books;
	}
}
