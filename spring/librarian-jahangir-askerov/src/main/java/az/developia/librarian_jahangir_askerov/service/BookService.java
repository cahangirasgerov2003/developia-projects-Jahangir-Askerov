package az.developia.librarian_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.config.ApplicationConfig;
import az.developia.librarian_jahangir_askerov.entity.BookEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.BookRepository;
import az.developia.librarian_jahangir_askerov.request.BookAddRequest;
import az.developia.librarian_jahangir_askerov.request.BookFilterRequest;
import az.developia.librarian_jahangir_askerov.request.BookUpdateRequest;
import az.developia.librarian_jahangir_askerov.request.ByCustomerFilterRequest;
import az.developia.librarian_jahangir_askerov.response.BookAddResponse;
import az.developia.librarian_jahangir_askerov.response.BookListResponse;
import az.developia.librarian_jahangir_askerov.response.BookSingleResponse;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import jakarta.validation.Valid;

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

	@Autowired
	private ApplicationConfig applicationConfig;
	
	@CacheEvict(value = "allBooks", key = "'all'")
	public BookAddResponse create(BookAddRequest book) {
		BookEntity bookEntity = modelMapper.map(book, BookEntity.class);

		bookEntity.setOperator_id(userService.findOperatorId());

		repository.save(bookEntity);
		return new BookAddResponse(bookEntity.getId());
	}

	@Cacheable(value = "allBooks", key = "'all'")
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

	public BookListResponse getBooksForStudentByName(String q) {
		q = q.toLowerCase();
		List<BookEntity> searchedBooks = repository.findAllByNameContaining(q);

		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();
		for (BookEntity book : searchedBooks) {
			BookSingleResponse resp = modelMapper.map(book, BookSingleResponse.class);
			mappedBooks.add(resp);
		}

		BookListResponse books = new BookListResponse();
		books.setBooks(mappedBooks);
		books.setTotalSize((long) mappedBooks.size());
		return books;
	}

	public BookListResponse getByFilter(BookFilterRequest filter) {

		String priceMin = filter.getPriceMin().trim();
		String priceMax = filter.getPriceMax().trim();

		final String DEFAULT_MAX_PRICE = String.valueOf(Integer.MAX_VALUE);

		if (priceMin.isEmpty() || priceMax.isEmpty()) {
			priceMin = priceMin.isEmpty() ? "0" : priceMin;
			priceMax = priceMax.isEmpty() ? DEFAULT_MAX_PRICE : priceMax;
		} else {
			if (Integer.parseInt(priceMin) > Integer.parseInt(priceMax))
				throw new MyException("Price minimum cannot be greater than price maximum", null, "Forbidden");
		}

		Long count = repository.getByFilterCount(userService.findOperatorId(), filter.getAuthor().trim().toLowerCase(),
				filter.getName().trim().toLowerCase(), priceMin, priceMax, filter.getPublishDate());

		if (count > applicationConfig.getBookCountLimit())
			throw new MyException("Too many results. Please refine your search", null, "TooManyResultsException");

		if (count == 0)
			throw new MyException("Unfortunately, nothing was found based on your search", null, "NotFoundException");

		List<BookEntity> filteredBooks = repository.getByFilter(userService.findOperatorId(),
				filter.getAuthor().trim().toLowerCase(), filter.getName().trim().toLowerCase(), priceMin, priceMax,
				filter.getPublishDate());

		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();

		for (BookEntity book : filteredBooks) {
			BookSingleResponse resp = modelMapper.map(book, BookSingleResponse.class);
			mappedBooks.add(resp);
		}

		BookListResponse books = new BookListResponse();
		books.setBooks(mappedBooks);

		return books;
	}

	public BookListResponse getByCustomerFilter(@Valid ByCustomerFilterRequest req) {
		Integer begin = (req.getPage() - 1) * req.getSize();
		String lowerName = req.getName().toLowerCase();

		Long filteredBooksCount = repository.getByCustomerFilterCount(req.getCategoryId(), lowerName);

		List<BookEntity> filteredBooks = repository.getByCustomerFilter(req.getCategoryId(), lowerName, begin,
				req.getSize());

		List<BookSingleResponse> mappedBooks = new ArrayList<BookSingleResponse>();

		for (BookEntity book : filteredBooks) {
			mappedBooks.add(modelMapper.map(book, BookSingleResponse.class));
		}

		BookListResponse books = new BookListResponse(mappedBooks, filteredBooksCount);

		return books;
	}

	public BookListResponse getPaginated(Integer page, Integer size) {
		if (page < 1) {
			throw new MyException(contentReader.readFromFile("invalidPaginationQuery.txt"), null,
					"InvalidPageNumberException");
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

//	@CachePut(value = "allBooks", key = "'all'")
	@CacheEvict(value = "allBooks", key = "'all'")
	public void updateById(Integer id, BookUpdateRequest book) {
		Optional<BookEntity> optionalBook = repository.findById(id);

		if (optionalBook.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookEntity existingBook = optionalBook.get();

		Integer bookOwnerId = existingBook.getOperator_id();

		if (userService.findOperatorId() != bookOwnerId) {
			throw new MyException(contentReader.readFromFile("forbidden.txt") + "book", null, "Forbidden");

		}

		modelMapper.map(book, existingBook);

		repository.save(existingBook);

	}

	@CacheEvict(value = "allBooks", key = "'all'")
	public void deleteById(Integer id) {
		Optional<BookEntity> optionalBook = repository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookEntity existingBook = optionalBook.get();

		Integer bookOwnerId = existingBook.getOperator_id();

		if (userService.findOperatorId() != bookOwnerId) {
			throw new MyException(contentReader.readFromFile("forbidden.txt") + "book", null, "Forbidden");
		}

		repository.deleteById(id);

	}

}
