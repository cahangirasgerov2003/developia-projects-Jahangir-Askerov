package az.developia.librarian_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import az.developia.librarian_jahangir_askerov.util.FileContentReader;

@Configuration
public class ApplicationConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public FileContentReader contentReader() {
		return new FileContentReader();
	}
}
