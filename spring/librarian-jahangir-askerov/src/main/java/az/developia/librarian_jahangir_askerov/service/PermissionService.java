package az.developia.librarian_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.PermissionEntity;
import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.repository.PermissionRepository;
import az.developia.librarian_jahangir_askerov.request.PermissionAddRequest;
import az.developia.librarian_jahangir_askerov.request.PermissionUpdateRequest;
import az.developia.librarian_jahangir_askerov.response.PermissionAddResponse;
import az.developia.librarian_jahangir_askerov.response.PermissionListResponse;
import az.developia.librarian_jahangir_askerov.response.PermissionSingleResponse;
import az.developia.librarian_jahangir_askerov.util.FileContentReader;
import jakarta.validation.Valid;

@RestController
public class PermissionService {

	@Autowired
	private PermissionRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FileContentReader contentReader;

	public PermissionAddResponse add(PermissionAddRequest req) {
		String authorityName = req.getAuthority();
//		Check if a authority exists based on their authority name
		Boolean authorityExists = repository.existsByAuthority(authorityName);

		if (authorityExists) {
			throw new MyException(contentReader.readFromFile("authorityAlreadyExists.txt"), null,
					"AuthorityAlreadyExistsException");
		}

		PermissionEntity permission = modelMapper.map(req, PermissionEntity.class);
		repository.save(permission);

		return new PermissionAddResponse(permission.getId());

	}

	public PermissionListResponse findAll() {
		List<PermissionEntity> allPermissions = repository.findAll();
		List<PermissionSingleResponse> mappedPermissions = new ArrayList<PermissionSingleResponse>();
		for (PermissionEntity permission : allPermissions) {
			PermissionSingleResponse resp = modelMapper.map(permission, PermissionSingleResponse.class);
			mappedPermissions.add(resp);
		}

		PermissionListResponse permissions = new PermissionListResponse();
		permissions.setPermissions(mappedPermissions);
		return permissions;
	}

	public PermissionSingleResponse findById(Integer id) {
		Optional<PermissionEntity> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "EntityNotFoundException");
		}

		PermissionSingleResponse permission = modelMapper.map(optional.get(), PermissionSingleResponse.class);
		return permission;
	}

	public PermissionListResponse findByName(String q) {
		List<PermissionEntity> searchedPermissions = repository.findAllByAuthorityContaining(q);
		List<PermissionSingleResponse> mappedPermissions = new ArrayList<PermissionSingleResponse>();
		for (PermissionEntity permission : searchedPermissions) {
			PermissionSingleResponse resp = modelMapper.map(permission, PermissionSingleResponse.class);
			mappedPermissions.add(resp);
		}

		PermissionListResponse permissions = new PermissionListResponse();
		permissions.setPermissions(mappedPermissions);
		return permissions;
	}

	public PermissionListResponse findAllPaginated(Integer page, Integer size) {
		if (page < 1 || size < 1) {
			throw new MyException(contentReader.readFromFile("invalidPaginationQuery.txt"), null,
					"InvalidPaginationQueryException");
		}
		page = (page - 1) * size;
		List<PermissionEntity> paginatedPermissions = repository.findAllPaginated(page, size);
		List<PermissionSingleResponse> mappedPermissions = new ArrayList<PermissionSingleResponse>();
		for (PermissionEntity permission : paginatedPermissions) {
			PermissionSingleResponse resp = modelMapper.map(permission, PermissionSingleResponse.class);
			mappedPermissions.add(resp);
		}

		PermissionListResponse permissions = new PermissionListResponse();
		permissions.setPermissions(mappedPermissions);
		return permissions;
	}

	public void deleteById(Integer id) {
		Optional<PermissionEntity> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "EntityNotFoundException");
		}

		repository.deleteById(id);
	}

	public void updateById(Integer id, @Valid PermissionUpdateRequest req) {
		Optional<PermissionEntity> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new MyException(contentReader.readFromFile("idNotFound.txt"), null, "EntityNotFoundException");
		}

		this.existsByAuthority(req.getAuthority());

		PermissionEntity existingPermission = optional.get();

		modelMapper.map(req, existingPermission);

		repository.save(existingPermission);
	}

	public void existsByAuthority(String authority) {
		boolean permissionExists = repository.existsByAuthority(authority);

		if (permissionExists) {
			throw new MyException("Permission " + contentReader.readFromFile("alreadyExists.txt"), null,
					"PermissionAlreadyExistsException");
		}
	}

}
