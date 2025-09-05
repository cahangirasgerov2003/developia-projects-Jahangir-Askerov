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
import az.developia.librarian_jahangir_askerov.request.StudentAddRequest;
import az.developia.librarian_jahangir_askerov.response.UserAddResponse;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;

@RestController
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void existsByUsername(String username) {
		boolean librarianExists = repository.existsByUsername(username);
		if (librarianExists) {
			throw new MyException("Username " + contentReader.readFromFile("alreadyExists.txt"), null,
					"UserAlreadyExistsException");
		}
	}

	public UserAddResponse addLibrarian(LibrarianAddRequest req) {
		return createUser(req, "librarian");

	}

	public String findOperatorUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}

	public UserEntity findOperatorByUsername() {
		String operatorUsername = findOperatorUsername();
		Optional<UserEntity> optional = repository.findByUsername(operatorUsername);

		if (!optional.isPresent()) {
			throw new MyException(contentReader.readFromFile("usernameNotFound.txt"), null,
					"UsernameNotFoundException");
		}

		return optional.get();
	}

	public Integer findOperatorId() {
		return findOperatorByUsername().getId();
	}

	public UserAddResponse addStudent(StudentAddRequest req) {
		return createUser(req, "student");
	}

	public UserAddResponse createUser(Object req, String type) {
		UserEntity entity = modelMapper.map(req, UserEntity.class);
		String unHashedPass = entity.getPassword();
		String encodedPass = "{bcrypt}" + passwordEncoder.encode(unHashedPass);
		entity.setEnabled(1);
		entity.setActive(1);
		entity.setType(type);
		entity.setPassword(encodedPass);
		repository.save(entity);

		return new UserAddResponse(entity.getId());
	}

}
