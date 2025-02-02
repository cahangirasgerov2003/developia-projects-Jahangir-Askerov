package az.developia.spring_java20_jahangir_askerov.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Repository {

	List<String> names = new ArrayList<String>();

	public List<String> getAllBooks() {
		names.add("A");
		names.add("B");
		return names;
	}

}
