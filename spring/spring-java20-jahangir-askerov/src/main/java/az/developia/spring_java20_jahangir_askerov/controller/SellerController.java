package az.developia.spring_java20_jahangir_askerov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.model.SellerEntity;
import az.developia.spring_java20_jahangir_askerov.model.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.service.SellerService;
import jakarta.validation.Valid;

@RestController // Inversion of control
@RequestMapping(path = "sellers")
public class SellerController {
	@Autowired // Dependency injection
	private SellerService service;

	@GetMapping(path = "/all")
	public List<SellerEntity> getAllSellers() {
		return service.getAllSellers();
	}

	@GetMapping(path = "/{id}")
	public SellerEntity getSellerByID(@PathVariable Integer id) {
		return service.getSellerByID(id);
	}

	@GetMapping(path = "/search")
	public List<SellerEntity> getSellersByName(@RequestParam(name = "name", defaultValue = "") String query) {
		return service.getSellersByName(query);
	}

	@PostMapping(path = "/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Integer createNewSeller(@Valid @RequestBody SellerEntity seller) {
		return service.createNewSeller(seller);
	}

	@PutMapping(path = "/{id}")
	public void updateSellerByID(@PathVariable Integer id, @Valid @RequestBody SellerUpdateRequest seller) {
		service.updateSellerByID(id, seller);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteSellerByID(@PathVariable Integer id) {
		service.deleteSellerByID(id);
	}

}
