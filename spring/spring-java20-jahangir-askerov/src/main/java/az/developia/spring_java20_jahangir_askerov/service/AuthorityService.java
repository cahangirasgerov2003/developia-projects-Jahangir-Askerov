package az.developia.spring_java20_jahangir_askerov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.repository.AuthorityRepository;
import az.developia.spring_java20_jahangir_askerov.response.AuthoritiesAddResponse;

@RestController
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	public AuthoritiesAddResponse addSellerAuthorities(String username) {
		Integer affectedRows = repository.addSellerAuthorities(username);
		return new AuthoritiesAddResponse(affectedRows);
	}

}
