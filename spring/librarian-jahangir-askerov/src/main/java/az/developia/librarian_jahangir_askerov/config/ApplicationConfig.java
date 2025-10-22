package az.developia.librarian_jahangir_askerov.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
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
@EnableCaching
public class ApplicationConfig {

	@Value("${book.count.limit}")
	private Integer bookCountLimit;

	@Value("${SPRING_DATASOURCE_USERNAME}")
	private String dbUser;

	@Value("${SPRING_DATASOURCE_PASSWORD}")
	private String dbPassword;

	@Value("${MYSQL_HOST:localhost}")
	private String dbHost;

	@Value("${MYSQL_PORT:3306}")
	private int dbPort;

	@Value("${MYSQL_DATABASE:librarian}")
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
