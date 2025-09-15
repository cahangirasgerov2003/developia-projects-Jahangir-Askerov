package az.developia.librarian_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.StudentEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.StudentRepository;
import az.developia.librarian_jahangir_askerov.request.StudentAddRequest;
import az.developia.librarian_jahangir_askerov.request.StudentFilterRequest;
import az.developia.librarian_jahangir_askerov.request.StudentUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.AuthorityAddResponse;
import az.developia.librarian_jahangir_askerov.response.StudentAddResponse;
import az.developia.librarian_jahangir_askerov.response.StudentListResponse;
import az.developia.librarian_jahangir_askerov.response.StudentSingleResponse;
import az.developia.librarian_jahangir_askerov.response.UserAddResponse;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
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

	@Autowired
	private FileContentReader contentReader;

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

		entity.setOperator_id(userService.findOperatorId());

		repository.save(entity);

//		Add authorities data to authorities table
		AuthorityAddResponse resp2 = authorityService.addStudentAuthorities(username);
		Integer affectedRows = resp2.getAffectedRows();

		return new StudentAddResponse(entity.getId(), user_id, affectedRows);

	}

	public void deleteById(Integer id) {
		Optional<StudentEntity> optionalStudent = repository.findById(id);

		if (optionalStudent.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		StudentEntity existingStudent = optionalStudent.get();

		if (userService.findOperatorId() != existingStudent.getOperator_id()) {
			throw new MyException(contentReader.readFromFile("forbidden.txt") + "student", null, "Forbidden");
		}

		repository.deleteById(id);
	}

	public void updateById(Integer id, StudentUpdateRequest req) {
		Optional<StudentEntity> optionalStudent = repository.findById(id);

		if (optionalStudent.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "NotFoundException");
		}

		StudentEntity existingStudent = optionalStudent.get();

		if (userService.findOperatorId() != existingStudent.getOperator_id()) {
			throw new MyException(contentReader.readFromFile("forbidden.txt") + "student", null, "Forbidden");
		}

		modelMapper.map(req, existingStudent);

		repository.save(existingStudent);
	}

	public StudentListResponse getByFilter(StudentFilterRequest req, Integer page, Integer size) {
		if (page < 1 || size < 1) {
			throw new MyException(contentReader.readFromFile("invalidPaginationQuery.txt"), null,
					"InvalidPageNumberException");
		}

		page = (page - 1) * size;

		req.setName(req.getName().toLowerCase());
		req.setSurname(req.getSurname().toLowerCase());
		
		Integer operatorId = userService.findOperatorId();
		
		
		System.out.println(operatorId + "OOOOOOOOOOOOOOOOOOOOOOOO");

		Long count = repository.getByFilterCount(operatorId, req.getName(), req.getSurname());

		if (count == 0)
			throw new MyException("Unfortunately, nothing was found based on your search", null, "NotFoundException");

		List<StudentEntity> filteredStudents = repository.getByFilter(operatorId, req.getName(),
				req.getSurname(), page, size);

		List<StudentSingleResponse> mappedStudents = new ArrayList<StudentSingleResponse>();

		filteredStudents.stream().forEach((student) -> {
			mappedStudents.add(modelMapper.map(student, StudentSingleResponse.class));
		});

		StudentListResponse response = new StudentListResponse(mappedStudents, count);

		return response;
	}

}
