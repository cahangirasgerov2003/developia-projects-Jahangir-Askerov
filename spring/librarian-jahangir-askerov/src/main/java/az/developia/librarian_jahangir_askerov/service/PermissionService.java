package az.developia.librarian_jahangir_askerov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.PermissionEntity;
import az.developia.librarian_jahangir_askerov.repository.PermissionRepository;
import az.developia.librarian_jahangir_askerov.request.PermissionAddRequest;
import az.developia.librarian_jahangir_askerov.response.PermissionAddResponse;

@RestController
public class PermissionService {

	@Autowired
	private PermissionRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public PermissionAddResponse add(PermissionAddRequest req) {
		PermissionEntity permission = modelMapper.map(req, PermissionEntity.class);
		repository.save(permission);

		return new PermissionAddResponse(permission.getId());

	}

}
