package az.developia.librarian_jahangir_askerov.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.BookBorrowEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.LendingRepository;
import az.developia.librarian_jahangir_askerov.request.BookBorrowRequest;
import az.developia.librarian_jahangir_askerov.response.BookBorrowResponse;
import az.developia.librarian_jahangir_askerov.response.StudentBorrowSingleResponse;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;

@RestController
public class LendingService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private LendingRepository repository;

	@Autowired
	private FileContentReader contentReader;

	public BookBorrowResponse borrow(BookBorrowRequest req) {
//		Check if this student currently has an active book purchased
//		???????????????????????????

		Long count = repository.findActiveBorrowCountByStudentId(req.getStudent_id());

		if (count > 0) {
			throw new MyException(contentReader.readFromFile("cannotBorrow.txt"), null, "ActiveBorrowExistsException");
		}

		BookBorrowEntity bookBorrowEntity = modelMapper.map(req, BookBorrowEntity.class);

		bookBorrowEntity.setLibrarian_id(userService.findOperatorId());

		repository.save(bookBorrowEntity);
		return new BookBorrowResponse(bookBorrowEntity.getId());
	}

	public StudentBorrowSingleResponse getById(Integer id) {
		Optional<BookBorrowEntity> optionalBookOnLoan = repository.findById(id);

		if (optionalBookOnLoan.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BookBorrowEntity bookOnLoan = optionalBookOnLoan.get();
		StudentBorrowSingleResponse customBookOnLoan = modelMapper.map(bookOnLoan, StudentBorrowSingleResponse.class);
		return customBookOnLoan;
	}

}
