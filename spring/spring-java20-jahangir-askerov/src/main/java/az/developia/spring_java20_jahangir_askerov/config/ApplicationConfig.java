package az.developia.spring_java20_jahangir_askerov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import az.developia.spring_java20_jahangir_askerov.service.BookService;

@Configuration
public class ApplicationConfig {
	@Bean
	@Primary
	public BookService createCustomBookService() {
		BookService customBookService = new BookService();
		return customBookService;
	}
}
