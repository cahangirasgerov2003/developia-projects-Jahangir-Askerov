package az.developia.librarian_jahangir_askerov.util.json;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class HttpMessageConvertor {

	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(ObjectMapper mapper) {
		SimpleModule stringModule = new SimpleModule();
		stringModule.addDeserializer(String.class, new JsonDeserializer<String>() {
			@Override
			public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				String text = p.getText();
				return text != null ? text.trim() : null;
			}
		});

		mapper.registerModule(stringModule);

		return new MappingJackson2HttpMessageConverter(mapper);
	}
}
