package az.developia.spring_java20_jahangir_askerov.practice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {
	String[] users = { "Cahangir", "Kamran", "Cuma", "Ali", "Azad" };

	@GetMapping(path = "")
	public String sayHello() {
		return "Welcome to our site !";
	}

	@GetMapping(path = "person")
	public Person getUserData() {
		Person person1 = new Person("Cahangir", "Asgerov");
		return person1;
	}

	@GetMapping(path = "person/{id}")
	public int getPathVariable(@PathVariable int id) {
		return id * id;
	}

	@GetMapping(path = "search")
	public List<String> getSearchedUsers(
			@RequestParam(name = "q", required = false, defaultValue = "") String userName) {
		List<String> searchedUsers = new ArrayList<String>();
		for (String user : users) {
			if (user.contains(userName)) {
				searchedUsers.add(user);
			}
		}

		return searchedUsers;

	}
}
