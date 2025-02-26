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
import az.developia.spring_java20_jahangir_askerov.response.AuthoritiesAddResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerAddResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerListResponse;
import az.developia.spring_java20_jahangir_askerov.response.SellerSingleResponse;
import az.developia.spring_java20_jahangir_askerov.response.UserAddResponse;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@RestController
public class SellerService {

	@Autowired
	private SellerRepository repository;

	@Autowired
	private FileContentReader contentReader;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	public SellerAddResponse create(SellerAddRequest seller) {
		String username = seller.getUsername();
//		Check if a user exists based on their user name
		userService.existsByUsername(username);

//		Add user data to users table
		UserAddResponse userAddResponse = userService.addSeller(seller);

//		Add seller data to seller table
		SellerEntity sellerEntity = modelMapper.map(seller, SellerEntity.class);
		sellerEntity.setUser_id(userAddResponse.getId());
		repository.save(sellerEntity);

//		Add authorities data to authorities table
		AuthoritiesAddResponse authoritiesAddResponse = authorityService.addSellerAuthorities(username);

		return new SellerAddResponse(sellerEntity.getId(), userAddResponse, authoritiesAddResponse);
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

	public SellerListResponse getPaginated(Integer page, Integer size) {
		if (page < 1) {
			throw new NotFoundException(contentReader.readFromFile("invalidPagination.txt"));
		}
		page = (page - 1) * size;
		List<SellerEntity> paginatedSellers = repository.getPaginated(page, size);
		List<SellerSingleResponse> mappedSellers = new ArrayList<SellerSingleResponse>();
		for (SellerEntity seller : paginatedSellers) {
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


		userService.updateSellerByID(existingSeller.getUser_id(), seller);
		
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
