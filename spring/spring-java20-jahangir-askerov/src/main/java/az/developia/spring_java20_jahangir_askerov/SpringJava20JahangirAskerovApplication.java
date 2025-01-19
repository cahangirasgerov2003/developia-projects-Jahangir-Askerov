package az.developia.spring_java20_jahangir_askerov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class SpringJava20JahangirAskerovApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJava20JahangirAskerovApplication.class, args);
    }

    @GetMapping
    public String sayHello() {
        return "Hello world !";
    }

    @GetMapping(path = "/person/create")
    public String createPerson() {
        Person person1 = new Person("Jahangir", "Askerov");
        String createdPerson = person1.toString();
        System.out.println(createdPerson);
        return createdPerson;
    }

}
