package az.developia.spring_java20_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import az.developia.spring_java20_jahangir_askerov.service.BookService;
import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@Configuration
public class ApplicationConfig {
	@Bean
	@Primary
	public BookService createCustomBookService() {
		BookService customBookService = new BookService();
		return customBookService;
	}

	@Bean
	@Primary
	public FileContentReader createFileContentReader() {
		FileContentReader fileContentReader = new FileContentReader();
		return fileContentReader;
	}

	@Bean
	@Primary
	public ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
