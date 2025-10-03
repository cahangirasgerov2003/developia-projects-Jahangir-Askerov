package az.developia.librarian_jahangir_askerov.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LibrarianBookCountEntity;
import az.developia.librarian_jahangir_askerov.repository.LibrarianBookCountRepository;
import az.developia.librarian_jahangir_askerov.response.LibrarianBookCountListResponse;
import az.developia.librarian_jahangir_askerov.response.LibrarianBookCountSingleResponse;

@RestController
public class LibrarianBookCountService {

	@Autowired
	private LibrarianBookCountRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public LibrarianBookCountListResponse getAll() {
		List<LibrarianBookCountEntity> allLibrarians = repository.findAll();
		List<LibrarianBookCountSingleResponse> mappedLibrarians = new ArrayList<LibrarianBookCountSingleResponse>();
		for (LibrarianBookCountEntity librarian : allLibrarians) {
			LibrarianBookCountSingleResponse resp = modelMapper.map(librarian, LibrarianBookCountSingleResponse.class);
			mappedLibrarians.add(resp);
		}
		LibrarianBookCountListResponse librarians = new LibrarianBookCountListResponse();
		librarians.setLibrariansBookCount(mappedLibrarians);
		librarians.setTotalSize((long) mappedLibrarians.size());
		return librarians;
	}

}
