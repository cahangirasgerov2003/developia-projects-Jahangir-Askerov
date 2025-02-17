package az.developia.spring_java20_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.model.SellerEntity;
import az.developia.spring_java20_jahangir_askerov.repository.SellerRepository;
import az.developia.spring_java20_jahangir_askerov.request.SellerAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.SellerListResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerSingleResponse;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@RestController
public class SellerService {

	@Autowired
	private SellerRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	public SellerListResponse getAllSellers() {
		List<SellerEntity> allSellers = repository.findAll();
		List<SellerSingleResponse> customAllSellers = new ArrayList<SellerSingleResponse>();
		for (SellerEntity seller : allSellers) {
			SellerSingleResponse sellerSingleResponse = new SellerSingleResponse();
			modelMapper.map(seller, sellerSingleResponse);
			customAllSellers.add(sellerSingleResponse);
		}
		SellerListResponse sellers = new SellerListResponse();
		sellers.setSellers(customAllSellers);
		return sellers;
	}

	public SellerSingleResponse getSellerByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		SellerSingleResponse customSeller = new SellerSingleResponse();
		SellerEntity seller = optionalSeller.get();
		modelMapper.map(seller, customSeller);
		return customSeller;
	}

	public SellerListResponse getSellersByName(String query) {
		List<SellerEntity> searchedSellers = repository.findAllByNameContaining(query);
		List<SellerSingleResponse> customSearchedSellers = new ArrayList<SellerSingleResponse>();
		for (SellerEntity seller : searchedSellers) {
			SellerSingleResponse sellerSingleResponse = new SellerSingleResponse();
			modelMapper.map(seller, sellerSingleResponse);
			customSearchedSellers.add(sellerSingleResponse);
		}
		SellerListResponse sellers = new SellerListResponse();
		sellers.setSellers(customSearchedSellers);
		return sellers;
	}

	public Integer createNewSeller(SellerAddRequest seller) {
		SellerEntity sellerEntity = new SellerEntity();
		modelMapper.map(seller, sellerEntity);
		repository.save(sellerEntity);
		return sellerEntity.getId();
	}

	public void updateSellerByID(Integer id, SellerUpdateRequest seller) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		SellerEntity existingSeller = optionalSeller.get();
		modelMapper.map(seller, existingSeller);

		repository.save(existingSeller);
	}

	public void deleteSellerByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		repository.deleteById(id);
	}

}
