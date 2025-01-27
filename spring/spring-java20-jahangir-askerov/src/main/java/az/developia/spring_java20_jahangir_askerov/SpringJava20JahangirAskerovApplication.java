package az.developia.spring_java20_jahangir_askerov;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("root-controller")
public class SpringJava20JahangirAskerovApplication {
	String[] users = { "Cahangir", "Kamran", "Cuma", "Ali", "Azad" };

	public static void main(String[] args) {
		SpringApplication.run(SpringJava20JahangirAskerovApplication.class, args);
	}

	@GetMapping(path = "")
	public String sayHello() {
		return "Welcome to our site !";
	}

	@GetMapping(path = "person")
	public Person getUserData() {
		Person person1 = new Person("Cahangir", "Asgerov");
		return person1;
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
