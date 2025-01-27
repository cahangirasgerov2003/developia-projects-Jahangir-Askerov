package az.developia.spring_java20_jahangir_askerov;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("my-controller")
@RestController
public class Controller2 {
	@GetMapping(path = "person")
	public String getUserData() {
		return "Hello";
	}

	@GetMapping(path = "person/{id}")
	public int getPathVariable(@PathVariable int id) {
		return id * id;
	}
}
