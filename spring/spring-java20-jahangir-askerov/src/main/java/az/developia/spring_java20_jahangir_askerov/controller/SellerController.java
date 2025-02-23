package az.developia.spring_java20_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import az.developia.spring_java20_jahangir_askerov.exception.ValidationException;
import az.developia.spring_java20_jahangir_askerov.request.SellerAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.SellerAddResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerListResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerSingleResponse;
import az.developia.spring_java20_jahangir_askerov.service.SellerService;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController // Inversion of control
@RequestMapping(path = "sellers")
public class SellerController {
	@Autowired // Dependency injection
	private SellerService service;

	@Autowired
	private FileContentReader contentReader;

	@PostMapping
	public ResponseEntity<SellerAddResponse> create(@Valid @RequestBody SellerAddRequest req, BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException(contentReader.readFromFile("validationMessage.txt"), br);
		}

		return new ResponseEntity<SellerAddResponse>(service.create(req), HttpStatus.CREATED);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<SellerListResponse> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<SellerSingleResponse> getByID(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getByID(id));
	}

	@GetMapping(path = "/search")
	public ResponseEntity<SellerListResponse> getByName(@RequestParam(name = "name", defaultValue = "") String q) {
		return ResponseEntity.ok(service.getByName(q));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateByID(@PathVariable Integer id, @Valid @RequestBody SellerUpdateRequest req,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new ValidationException(contentReader.readFromFile("validationMessage.txt"), br);
		}
		service.updateByID(id, req);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteByID(@PathVariable Integer id) {
		service.deleteByID(id);
		return ResponseEntity.noContent().build();
	}

}
