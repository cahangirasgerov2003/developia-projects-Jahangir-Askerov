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
import az.developia.librarian_jahangir_askerov.util.FileContentReader;
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
	public ResponseEntity<StudentAddResponse> create(@Valid @RequestBody StudentAddRequest req, BindingResult br) {

		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		return new ResponseEntity<StudentAddResponse>(service.create(req), HttpStatus.CREATED);
	}
	
	

}
