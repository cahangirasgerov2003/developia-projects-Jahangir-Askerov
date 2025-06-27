package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.request.PermissionAddRequest;
import az.developia.librarian_jahangir_askerov.request.PermissionUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.PermissionAddResponse;
import az.developia.librarian_jahangir_askerov.response.PermissionListResponse;
import az.developia.librarian_jahangir_askerov.response.PermissionSingleResponse;
import az.developia.librarian_jahangir_askerov.service.PermissionService;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController {

	@Autowired
	private PermissionService service;

	@Autowired
	private FileContentReader contentReader;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_PERMISSION')")
	public ResponseEntity<PermissionAddResponse> add(@Valid @RequestBody PermissionAddRequest req, BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

//		Check if a permission with the given authority name already exists
		service.existsByAuthority(req.getAuthority());

		return new ResponseEntity<PermissionAddResponse>(service.add(req), HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_ALL_PERMISSIONS')")
	public ResponseEntity<PermissionListResponse> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_BY_ID_PERMISSION')")
	public ResponseEntity<PermissionSingleResponse> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/search")
	@PreAuthorize(value = "hasAuthority('ROLE_SEARCH_PERMISSIONS')")
	public ResponseEntity<PermissionListResponse> findByName(@RequestParam(name = "name", defaultValue = "") String q) {
		return ResponseEntity.ok(service.findByName(q));
	}

	@GetMapping("/paginate")
	@PreAuthorize(value = "hasAuthority('ROLE_PAGINATE_PERMISSIONS')")
	public ResponseEntity<PermissionListResponse> findAllPaginated(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size) {
		return ResponseEntity.ok(service.findAllPaginated(page, size));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_PERMISSION')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_PERMISSION')")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @Valid @RequestBody PermissionUpdateRequest req,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new MyException(contentReader.readFromFile("validationException.txt"), br, "ValidationException");
		}

//		Check if the updated permission name already exists in the database

		service.updateById(id, req);
		return ResponseEntity.noContent().build();
	}
}
