package az.developia.spring_java20_jahangir_askerov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.entity.UserEntity;
import az.developia.spring_java20_jahangir_askerov.exception.UserAlreadyExistsException;
import az.developia.spring_java20_jahangir_askerov.repository.UserRepository;
import az.developia.spring_java20_jahangir_askerov.request.SellerAddRequest;
import az.developia.spring_java20_jahangir_askerov.response.UserAddResponse;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@RestController
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

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

	public void existsByUsername(String username) {
		Boolean userExists = repository.existsByUsername(username);
		if (userExists) {
			throw new UserAlreadyExistsException(new FileContentReader().readFromFile("/userAlreadyExists.txt"),
					username);
		}
	}

}
