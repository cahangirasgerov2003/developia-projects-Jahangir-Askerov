package az.developia.librarian_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.BookEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.BookRepository;
import az.developia.librarian_jahangir_askerov.request.BookAddRequest;
import az.developia.librarian_jahangir_askerov.request.BookUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.BookAddResponse;
import az.developia.librarian_jahangir_askerov.response.BookListResponse;
import az.developia.librarian_jahangir_askerov.response.BookSingleResponse;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;

@RestController
public class BookService {
	@Autowired
	private BookRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	public BookAddResponse create(BookAddRequest book) {
		BookEntity bookEntity = modelMapper.map(book, BookEntity.class);

		bookEntity.setOperator_id(userService.findOperatorId());

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
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookEntity book = optionalBook.get();
		BookSingleResponse customBook = modelMapper.map(book, BookSingleResponse.class);
		return customBook;
	}

	public BookListResponse getByName(String q) {
		q = q.toLowerCase();

		List<BookEntity> searchedBooks = repository.findAllByNameContaining(q, userService.findOperatorId());

		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : searchedBooks) {
			BookSingleResponse resp = modelMapper.map(book, BookSingleResponse.class);
			mappedBooks.add(resp);
		}
		BookListResponse books = new BookListResponse();
		books.setBooks(mappedBooks);
		return books;
	}

	public BookListResponse getPaginated(Integer page, Integer size) {
		if (page < 1) {
			throw new MyException(contentReader.readFromFile("invalidPagination.txt"), null, "NotFoundException");
		}
		page = (page - 1) * size;
		List<BookEntity> paginatedBooks = repository.getPaginated(page, size);
		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : paginatedBooks) {
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
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookEntity existingBook = optionalBook.get();

		Integer bookOwnerId = existingBook.getOperator_id();

		if (userService.findOperatorId() != bookOwnerId) {
			throw new MyException(contentReader.readFromFile("forbidden.txt"), null, "Forbidden");

		}

		modelMapper.map(book, existingBook);

		repository.save(existingBook);

	}

	public void deleteById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookEntity existingBook = optionalBook.get();

		Integer bookOwnerId = existingBook.getOperator_id();

		if (userService.findOperatorId() != bookOwnerId) {
			throw new MyException(contentReader.readFromFile("forbidden.txt"), null, "Forbidden");
		}

		repository.deleteById(id);

	}

}
