package az.developia.spring_java20_jahangir_askerov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringJava20JahangirAskerovApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJava20JahangirAskerovApplication.class,
				args);

		Integer beanDefinitionCount = context.getBeanDefinitionCount();

		System.out.println("Bean definition count : " + beanDefinitionCount);

//		for (String name : context.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
	}

}
