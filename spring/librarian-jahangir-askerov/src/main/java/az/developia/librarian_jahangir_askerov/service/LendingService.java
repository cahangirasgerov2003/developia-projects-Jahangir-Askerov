package az.developia.librarian_jahangir_askerov.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.BorrowedBookEntity;
import az.developia.librarian_jahangir_askerov.entity.LendingEntity;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.model.Address;
import az.developia.librarian_jahangir_askerov.repository.BorrowedBookDetailsRepository;
import az.developia.librarian_jahangir_askerov.repository.LendingRepository;
import az.developia.librarian_jahangir_askerov.request.BorrowedBookUpdateRequest;
import az.developia.librarian_jahangir_askerov.request.LendingRequest;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookDetailsListResponse;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookDetailsResponse;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookListResponse;
import az.developia.librarian_jahangir_askerov.response.BorrowedBookSingleResponse;
import az.developia.librarian_jahangir_askerov.response.LendingResponse;
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
	private BorrowedBookDetailsRepository borrowedBookRepository;

	@Autowired
	private FileContentReader contentReader;

	public LendingResponse borrow(LendingRequest req) {

		Long count = repository.countActiveLoansByStudentId(req.getStudent_id());

		if (count > 0) {
			throw new MyException(contentReader.readFromFile("cannotBorrow.txt"), null, "ActiveBorrowExistsException");
		}

		LendingEntity lendingEntity = modelMapper.map(req, LendingEntity.class);

		lendingEntity.setLibrarian_id(userService.findOperatorId());

		repository.save(lendingEntity);
		return new LendingResponse(lendingEntity.getId());
	}

	public BorrowedBookListResponse getAll() {
		Integer operatorId = userService.findOperatorId();
		List<LendingEntity> allBorrowedBooks = repository.findLoansByLibrarianId(operatorId);
		List<BorrowedBookSingleResponse> mappedBorrowedBooks = new ArrayList<BorrowedBookSingleResponse>();
		for (LendingEntity borrowedBook : allBorrowedBooks) {
			BorrowedBookSingleResponse resp = modelMapper.map(borrowedBook, BorrowedBookSingleResponse.class);
			mappedBorrowedBooks.add(resp);
		}
		BorrowedBookListResponse borrowedBooks = new BorrowedBookListResponse();
		borrowedBooks.setBooksOnLoan(mappedBorrowedBooks);
		borrowedBooks.setTotalSize(allBorrowedBooks.stream().count());
		return borrowedBooks;
	}

	public BorrowedBookDetailsResponse getById(Integer id) {
		Optional<BorrowedBookEntity> optional = borrowedBookRepository.findById(id);
		return returnBorrowedBookResponse(optional);
	}

	public BorrowedBookDetailsListResponse getByBorrowDateBetween(LocalDate startDate, LocalDate endDate) {
		Integer operatorId = userService.findOperatorId();
		List<BorrowedBookEntity> allBorrowedBooks = borrowedBookRepository
				.findByLibrarianIdentityAndBorrowDateBetween(operatorId, startDate, endDate);

		return returnResponse(allBorrowedBooks);

	}

	public BorrowedBookDetailsListResponse getReturnedByBorrowDateBetween(LocalDate startDate, LocalDate endDate,
			LendingStatus status) {
		Integer operatorId = userService.findOperatorId();
		List<BorrowedBookEntity> allBorrowedBooks = borrowedBookRepository
				.findByLibrarianIdentityAndStatusAndBorrowDateBetween(operatorId, status, startDate, endDate);

		return returnResponse(allBorrowedBooks);

	}

	public BorrowedBookDetailsListResponse getOverdued() {
		Integer operatorId = userService.findOperatorId();
		List<BorrowedBookEntity> allBorrowedBooks = borrowedBookRepository.findOverdued(operatorId);

		return returnResponse(allBorrowedBooks);
	}

	public BorrowedBookDetailsResponse returnBorrowedBookResponse(Optional<BorrowedBookEntity> optional) {
		if (optional.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		BorrowedBookEntity borrowedBook = optional.get();
		BorrowedBookDetailsResponse response = modelMapper.map(borrowedBook, BorrowedBookDetailsResponse.class);
		response.setAddress(new Address(null, borrowedBook.getCity(), borrowedBook.getCountry(), null));
		;

		return response;
	}

	public BorrowedBookDetailsListResponse returnResponse(List<BorrowedBookEntity> allBorrowedBooks) {
		List<BorrowedBookDetailsResponse> mappedBorrowedBooks = new ArrayList<BorrowedBookDetailsResponse>();
		for (BorrowedBookEntity borrowedBook : allBorrowedBooks) {
			BorrowedBookDetailsResponse resp = modelMapper.map(borrowedBook, BorrowedBookDetailsResponse.class);
			mappedBorrowedBooks.add(resp);
		}
		BorrowedBookDetailsListResponse borrowedBooks = new BorrowedBookDetailsListResponse();
		borrowedBooks.setResponses(mappedBorrowedBooks);
		borrowedBooks.setTotalSize(allBorrowedBooks.stream().count());

		return borrowedBooks;
	}

	public void updateById(Integer id, BorrowedBookUpdateRequest req) {
		Optional<LendingEntity> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		LendingEntity existingBorrowedBook = optional.get();

		Integer borrowedBookOwnerId = existingBorrowedBook.getLibrarian_id();

		if (userService.findOperatorId() != borrowedBookOwnerId) {
			throw new MyException(contentReader.readFromFile("forbidden.txt") + "book", null, "Forbidden");

		}

		modelMapper.map(req, existingBorrowedBook);
		existingBorrowedBook.setActualReturnDate(LocalDate.now());

		repository.save(existingBorrowedBook);
	}

}
