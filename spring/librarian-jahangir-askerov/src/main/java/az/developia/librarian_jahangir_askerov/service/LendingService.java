package az.developia.librarian_jahangir_askerov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LendingEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.LendingRepository;
import az.developia.librarian_jahangir_askerov.request.LendingRequest;
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

}
