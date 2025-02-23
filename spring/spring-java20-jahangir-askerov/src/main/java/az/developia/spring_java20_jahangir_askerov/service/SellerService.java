package az.developia.spring_java20_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.entity.SellerEntity;
import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;
import az.developia.spring_java20_jahangir_askerov.repository.SellerRepository;
import az.developia.spring_java20_jahangir_askerov.request.SellerAddRequest;
import az.developia.spring_java20_jahangir_askerov.request.SellerUpdateRequest;
import az.developia.spring_java20_jahangir_askerov.response.SellerAddResponse;
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

	public SellerAddResponse create(SellerAddRequest seller) {
		SellerEntity sellerEntity = modelMapper.map(seller, SellerEntity.class);
		repository.save(sellerEntity);
		return new SellerAddResponse(sellerEntity.getId());
	}

	public SellerListResponse getAll() {
		List<SellerEntity> allSellers = repository.findAll();
		List<SellerSingleResponse> mappedSellers = new ArrayList<SellerSingleResponse>();
		for (SellerEntity seller : allSellers) {
			SellerSingleResponse resp = modelMapper.map(seller, SellerSingleResponse.class);
			mappedSellers.add(resp);
		}
		SellerListResponse sellers = new SellerListResponse();
		sellers.setSellers(mappedSellers);
		return sellers;
	}

	public SellerSingleResponse getByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		SellerEntity seller = optionalSeller.get();
		SellerSingleResponse customSeller = modelMapper.map(seller, SellerSingleResponse.class);
		return customSeller;
	}

	public SellerListResponse getByName(String q) {
		List<SellerEntity> searchedSellers = repository.findAllByNameContaining(q);
		List<SellerSingleResponse> mappedSellers = new ArrayList<SellerSingleResponse>();
		for (SellerEntity seller : searchedSellers) {
			SellerSingleResponse resp = modelMapper.map(seller, SellerSingleResponse.class);
			mappedSellers.add(resp);
		}
		SellerListResponse sellers = new SellerListResponse();
		sellers.setSellers(mappedSellers);
		return sellers;
	}

	public void updateByID(Integer id, SellerUpdateRequest seller) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		SellerEntity existingSeller = optionalSeller.get();
		modelMapper.map(seller, existingSeller);

		repository.save(existingSeller);
	}

	public void deleteByID(Integer id) {
		Optional<SellerEntity> optionalSeller = repository.findById(id);
		if (optionalSeller.isEmpty()) {
			throw new NotFoundException(contentReader.readFromFile("idNotFound.txt"));
		}

		repository.deleteById(id);
	}

}
