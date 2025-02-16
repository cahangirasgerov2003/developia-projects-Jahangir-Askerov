package az.developia.spring_java20_jahangir_askerov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.model.SellerEntity;
import az.developia.spring_java20_jahangir_askerov.model.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.repository.SellerRepository;

@RestController
public class SellerService {

	@Autowired
	private SellerRepository repository;

	public List<SellerEntity> getAllSellers() {
		return repository.findAll();
	}

	public SellerEntity getSellerByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException("The seller with the ID you are looking for does not exist !");
		}

		return optionalSeller.get();
	}

	public List<SellerEntity> getSellersByName(String query) {
		return repository.findAllByNameContaining(query);
	}

	public Integer createNewSeller(SellerEntity seller) {
		repository.save(seller);
		return seller.getId();
	}

	public void updateSellerByID(Integer id, SellerUpdateRequest seller) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException("The seller with the ID you are looking for does not exist !");
		}

		SellerEntity existingSeller = optionalSeller.get();
		existingSeller.setUsername(seller.getUsername());
		existingSeller.setPassword(seller.getPassword());
		existingSeller.setEmail(seller.getEmail());
		existingSeller.getAddress().setCountry(seller.getAddress().getCountry());
		existingSeller.getAddress().setCity(seller.getAddress().getCity());
		existingSeller.getAddress().setStreet(seller.getAddress().getStreet());
		existingSeller.getAddress().setZipCode(seller.getAddress().getZipCode());

		repository.save(existingSeller);
	}

	public void deleteSellerByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException("The seller with the ID you are looking for does not exist !");
		}

		repository.deleteById(id);
	}

}
