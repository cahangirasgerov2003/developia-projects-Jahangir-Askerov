package az.developia.librarian_jahangir_askerov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.BookBorrowEntity;
import az.developia.librarian_jahangir_askerov.repository.LendingRepository;
import az.developia.librarian_jahangir_askerov.request.BookBorrowRequest;
import az.developia.librarian_jahangir_askerov.response.BookBorrowResponse;

@RestController
public class LendingService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private LendingRepository repository;

	public BookBorrowResponse borrow(BookBorrowRequest req) {
		BookBorrowEntity bookBorrowEntity = modelMapper.map(req, BookBorrowEntity.class);

		bookBorrowEntity.setLibrarian_id(userService.findOperatorId());

		repository.save(bookBorrowEntity);
		return new BookBorrowResponse(bookBorrowEntity.getId());
	}

}
