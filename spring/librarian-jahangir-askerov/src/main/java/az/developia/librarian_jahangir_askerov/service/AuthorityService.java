package az.developia.librarian_jahangir_askerov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.repository.AuthorityRepository;
import az.developia.librarian_jahangir_askerov.response.AuthorityAddResponse;

@RestController
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	public AuthorityAddResponse addLibrarianAuthorities(String username) {
		Integer affectedRows = repository.addLibrarianAuthorities(username);
		return new AuthorityAddResponse(affectedRows);
	}

	public AuthorityAddResponse addStudentAuthorities(String username) {
		Integer affectedRows = repository.addStudentAuthorities(username);
		return new AuthorityAddResponse(affectedRows);
	}

}
