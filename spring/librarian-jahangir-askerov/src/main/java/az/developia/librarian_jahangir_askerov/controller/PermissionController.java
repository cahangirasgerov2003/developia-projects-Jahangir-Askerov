package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.PermissionAddRequest;
import az.developia.librarian_jahangir_askerov.response.PermissionAddResponse;
import az.developia.librarian_jahangir_askerov.service.PermissionService;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController {

	@Autowired
	private PermissionService service;

	@PostMapping
//	@PreAuthorize(value = "hasAuthority('ROLE_ADD_PERMISSION')")
	public ResponseEntity<PermissionAddResponse> add(@Valid @RequestBody PermissionAddRequest req, BindingResult br) {
		FileContentReader fileContentReader = new FileContentReader();
		if (br.hasErrors()) {
			throw new MyException(fileContentReader.readFromFile("validationException.txt"), br, "ValidationException" );
		}
		return new ResponseEntity<PermissionAddResponse>(service.add(req), HttpStatus.CREATED);
	}
}
