package az.developia.librarian_jahangir_askerov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.StudentEntity;
import az.developia.librarian_jahangir_askerov.repository.StudentRepository;
import az.developia.librarian_jahangir_askerov.request.StudentAddRequest;
import az.developia.librarian_jahangir_askerov.response.AuthorityAddResponse;
import az.developia.librarian_jahangir_askerov.response.StudentAddResponse;
import az.developia.librarian_jahangir_askerov.response.UserAddResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@Transactional(rollbackOn = Exception.class)
public class StudentService {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthorityService authorityService;

	public StudentAddResponse create(@Valid StudentAddRequest req) {
		String username = req.getUsername();

//		Check if a user exists based on their user name
		userService.existsByUsername(username);

//		Add user data to users table
		UserAddResponse resp = userService.addStudent(req);
		Integer user_id = resp.getUser_id();

//		Add student data to students table
		StudentEntity entity = modelMapper.map(req, StudentEntity.class);
		entity.setUserId(user_id);
		
		entity.setOperatorId(userService.findOperatorId());
		
		repository.save(entity);

//		Add authorities data to authorities table
		AuthorityAddResponse resp2 = authorityService.addStudentAuthorities(username);
		Integer affectedRows = resp2.getAffectedRows();

		return new StudentAddResponse(entity.getId(), user_id, affectedRows);

	}

}
