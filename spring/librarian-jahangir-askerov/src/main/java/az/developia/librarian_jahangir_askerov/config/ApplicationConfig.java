package az.developia.librarian_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableScheduling
public class ApplicationConfig {

	@Value(value = "${book.count.limit}")
	private Integer bookCountLimit;

	@Value("${DB_USER}")
	private String dbUser;

	@Value("${DB_PASSWORD}")
	private String dbPassword;

	@Value("${DB_HOST}")
	private String dbHost;

	@Value("${DB_PORT}")
	private int dbPort;

	@Value("${DB_NAME}")
	private String dbName;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public FileContentReader contentReader() {
		return new FileContentReader();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
