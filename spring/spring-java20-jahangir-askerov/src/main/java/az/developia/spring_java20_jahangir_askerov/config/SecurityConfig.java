package az.developia.spring_java20_jahangir_askerov.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;

}
