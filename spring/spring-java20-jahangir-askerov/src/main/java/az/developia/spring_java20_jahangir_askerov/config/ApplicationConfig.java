package az.developia.spring_java20_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import az.developia.spring_java20_jahangir_askerov.util.FileContentReader;

@Configuration
public class ApplicationConfig {

	@Bean
	public FileContentReader fileContentReader() {
		FileContentReader fileContentReader = new FileContentReader();
		return fileContentReader;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
