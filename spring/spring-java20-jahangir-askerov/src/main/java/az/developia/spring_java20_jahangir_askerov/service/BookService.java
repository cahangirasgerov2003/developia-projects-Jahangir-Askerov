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
import az.developia.spring_java20_jahangir_askerov.response.BookAddResponse;
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

	public BookAddResponse create(BookAddRequest book) {
		BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
		repository.save(bookEntity);
		return new BookAddResponse(bookEntity.getId());
	}

	public BookListResponse getAll() {
		List<BookEntity> allBooks = repository.findAll();
		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : allBooks) {
			BookSingleResponse resp = modelMapper.map(book, BookSingleResponse.class);
			mappedBooks.add(resp);
		}
		BookListResponse books = new BookListResponse();
		books.setBooks(mappedBooks);
		return books;
	}

	public BookSingleResponse getById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		BookEntity book = optionalBook.get();
		BookSingleResponse customBook = modelMapper.map(book, BookSingleResponse.class);
		return customBook;
	}

	public BookListResponse getByName(String q) {
		List<BookEntity> searchedBooks = repository.findAllByNameContaining(q);
		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : searchedBooks) {
			BookSingleResponse resp = modelMapper.map(book, BookSingleResponse.class);
			mappedBooks.add(resp);
		}
		BookListResponse books = new BookListResponse();
		books.setBooks(mappedBooks);
		return books;
	}

	public void updateById(Integer id, BookUpdateRequest book) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		BookEntity existingBook = optionalBook.get();

		modelMapper.map(book, existingBook);

		repository.save(existingBook);

	}

	public void deleteById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		repository.deleteById(id);
	}

}
