package az.developia.librarian_jahangir_askerov.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LibrarianEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.LibrarianRepository;
import az.developia.librarian_jahangir_askerov.request.LibrarianAddRequest;
import az.developia.librarian_jahangir_askerov.response.AuthorityAddResponse;
import az.developia.librarian_jahangir_askerov.response.LibrarianAddResponse;
import az.developia.librarian_jahangir_askerov.response.LibrarianSingleResponse;
import az.developia.librarian_jahangir_askerov.response.UserAddResponse;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import jakarta.transaction.Transactional;

@RestController
@Transactional(rollbackOn = Exception.class)
public class LibrarianService {

	@Autowired
	private LibrarianRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FileContentReader contentReader;

	public LibrarianAddResponse add(LibrarianAddRequest req) {

		String username = req.getUsername();
//		Check if a user exists based on their user name
		userService.existsByUsername(username);

//		Add user data to users table
		UserAddResponse userAddResponse = userService.addLibrarian(req);
		Integer user_id = userAddResponse.getUser_id();

//		Add librarian data to librarians table
		LibrarianEntity librarianEntity = modelMapper.map(req, LibrarianEntity.class);
		librarianEntity.setUser_id(userAddResponse.getUser_id());
		repository.save(librarianEntity);

//		Add authorities data to authorities table
		AuthorityAddResponse authorityAddResponse = authorityService.addLibrarianAuthorities(username);
		Integer affectedRows = authorityAddResponse.getAffectedRows();

		return new LibrarianAddResponse(librarianEntity.getId(), user_id, affectedRows);
	}

	public LibrarianSingleResponse getLibrarianByOperatorId(Integer operator_id) {
		Optional<LibrarianEntity> optionalLibrarian = repository.findById(operator_id);

		if (optionalLibrarian.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		LibrarianSingleResponse response = modelMapper.map(optionalLibrarian.get(), LibrarianSingleResponse.class);

		return response;
	}

}
