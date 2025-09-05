package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.StudentAddRequest;
import az.developia.librarian_jahangir_askerov.response.StudentAddResponse;
import az.developia.librarian_jahangir_askerov.service.StudentService;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private StudentService service;

	@PreAuthorize(value = "hasAuthority('ROLE_ADD_STUDENT')")
	@PostMapping
	@Operation(summary = "Through this method, the librarian can register new students", description = "This endpoint validates the student data and creates a new student in the system. "
			+ "If validation fails, a ValidationException is thrown with detailed field errors.")
	public ResponseEntity<StudentAddResponse> create(@Valid @RequestBody StudentAddRequest req, BindingResult br) {

		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		return new ResponseEntity<StudentAddResponse>(service.create(req), HttpStatus.CREATED);
	}

}
