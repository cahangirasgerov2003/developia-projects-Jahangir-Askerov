package az.developia.librarian_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import az.developia.librarian_jahangir_askerov.util.FileContentReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConfig {

	@Value(value = "${book.count.limit}")
	private Integer bookCountLimit;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public FileContentReader contentReader() {
		return new FileContentReader();
	}
}
