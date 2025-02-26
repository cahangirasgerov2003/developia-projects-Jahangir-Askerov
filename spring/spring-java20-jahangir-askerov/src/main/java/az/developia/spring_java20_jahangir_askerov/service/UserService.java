package az.developia.spring_java20_jahangir_askerov.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.entity.UserEntity;
import az.developia.spring_java20_jahangir_askerov.exception.MyException;
import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.exception.UserAlreadyExistsException;
import az.developia.spring_java20_jahangir_askerov.repository.UserRepository;
import az.developia.spring_java20_jahangir_askerov.request.SellerAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.UserAddResponse;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@RestController
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserAddResponse addSeller(SellerAddRequest seller) {
		UserEntity userEntity = modelMapper.map(seller, UserEntity.class);
		String unHashedPass = userEntity.getPassword();
		String encodedPass = "{bcrypt}" + new BCryptPasswordEncoder().encode(unHashedPass);
		userEntity.setEnabled(1);
		userEntity.setType("seller");
		userEntity.setPassword(encodedPass);
		repository.save(userEntity);
		return new UserAddResponse(userEntity.getId());
	}

	public void updateSellerByID(Integer user_id, SellerUpdateRequest seller) {
		
		Optional<UserEntity> optionalSeller = repository.findById(user_id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		UserEntity existingSeller = optionalSeller.get();

		System.out.println(seller.getCurrent_password());
		System.out.println(existingSeller.getPassword());

		if (!(passwordEncoder.matches(seller.getCurrent_password(), existingSeller.getPassword().substring(8)))) {
			throw new MyException(contentReader.readFromFile("currentPasswordIncorrect.txt"));
		}

		System.out.println("Good");
		
		modelMapper.map(seller, existingSeller);

		String newPassword = passwordEncoder.encode(seller.getPassword());

		existingSeller.setPassword(newPassword);

		repository.save(existingSeller);
	}

	public void existsByUsername(String username) {
		Boolean userExists = repository.existsByUsername(username);
		if (userExists) {
			throw new UserAlreadyExistsException(contentReader.readFromFile("userAlreadyExists.txt"), username);
		}
	}

}
