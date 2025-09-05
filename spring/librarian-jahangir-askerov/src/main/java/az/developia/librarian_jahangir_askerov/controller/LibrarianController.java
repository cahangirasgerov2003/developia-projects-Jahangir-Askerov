package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.LibrarianAddRequest;
import az.developia.librarian_jahangir_askerov.response.LibrarianAddResponse;
import az.developia.librarian_jahangir_askerov.service.LibrarianService;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

	@Autowired
	private LibrarianService service;

	@Autowired
	private FileContentReader contentReader;

	@PostMapping
	public ResponseEntity<LibrarianAddResponse> add(@Valid @RequestBody LibrarianAddRequest req, BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}
		return new ResponseEntity<LibrarianAddResponse>(service.add(req), HttpStatus.CREATED);
	}

}
