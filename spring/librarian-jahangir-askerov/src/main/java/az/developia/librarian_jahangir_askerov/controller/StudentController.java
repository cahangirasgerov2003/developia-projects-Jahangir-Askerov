package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.StudentAddRequest;
import az.developia.librarian_jahangir_askerov.request.StudentFilterRequest;
import az.developia.librarian_jahangir_askerov.request.StudentUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.StudentAddResponse;
import az.developia.librarian_jahangir_askerov.response.StudentListResponse;
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

	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_STUDENT')")
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Delete a student by ID", description = "This endpoint allows the librarian to delete a student from the system using the student's unique ID. ")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_STUDENT')")
	@PutMapping(path = "/{id}")
	@Operation(summary = "Update an existing student by ID", description = "This endpoint allows the librarian to update the details of an existing student using their unique ID. ")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @Valid @RequestBody StudentUpdateRequest req,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		service.updateById(id, req);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/filter")
	@PreAuthorize(value = "hasAuthority('ROLE_FILTER_STUDENTS')")
	public ResponseEntity<StudentListResponse> getByFilter(@Valid @RequestBody StudentFilterRequest req,
			BindingResult br, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size) { 
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

		return ResponseEntity.ok(service.getByFilter(req, page, size));

	}

}
