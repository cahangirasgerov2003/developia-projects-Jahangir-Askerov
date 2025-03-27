package az.developia.librarian_jahangir_askerov.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.UserEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.UserRepository;
import az.developia.librarian_jahangir_askerov.request.LibrarianAddRequest;
import az.developia.librarian_jahangir_askerov.response.UserAddResponse;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;

@RestController
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	public void existsByUsername(String username) {
		boolean librarianExists = repository.existsByUsername(username);
		if (librarianExists) {
			throw new MyException(contentReader.readFromFile("userAlreadyExists.txt"), null,
					"UserAlreadyExistsException");
		}
	}

	public UserAddResponse addLibrarian(LibrarianAddRequest req) {
		UserEntity userEntity = modelMapper.map(req, UserEntity.class);
		String unHashedPass = userEntity.getPassword();
		String encodedPass = "{bcrypt}" + new BCryptPasswordEncoder().encode(unHashedPass);
		userEntity.setEnabled(1);
		userEntity.setActive(1);
		userEntity.setType("librarian");
		userEntity.setPassword(encodedPass);
		repository.save(userEntity);

		return new UserAddResponse(userEntity.getId());
	}

	public String findOperatorUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}

	public UserEntity findOperatorByUsername(String username) {
		Optional<UserEntity> op = repository.findByUsername(username);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new MyException("User not found", null, "UserNotFoundException");

		}

	}

}
