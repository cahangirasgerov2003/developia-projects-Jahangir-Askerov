package az.developia.librarian_jahangir_askerov.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.exception.MyException;

@RestController
public class ErrorTranslationService {

	@Autowired
	private MessageSource messageSource;

	public String getByWord(Locale locale, String word) {

		return messageSource.getMessage(word, null, locale);

//		Use Null or for default value new Object[]{} - use this array

	}

	public Map<String, String> getAllByLanguage(Locale locale) {
		String fileName = "messages_" + locale.toString() + ".properties";

//		classpath içində yerləşən faylları oxumaq üçündür.

//      classpath dedikdə src/main/resources qovluğu nəzərdə tutulur (və ya jar-ın içində yerləşən resources).

		try {
			ClassPathResource resource = new ClassPathResource(fileName);

			Properties props = new Properties();

			props.load(resource.getInputStream());

			return props.entrySet().stream()
					.collect(Collectors.toMap(e -> String.valueOf(e.getKey()), e -> String.valueOf(e.getValue())));

		} catch (FileNotFoundException e) {
			throw new MyException("File not found: " + fileName, null, e.getClass().getSimpleName());
		} catch (IOException e) {
			throw new MyException("Error reading file: " + fileName, null, e.getClass().getSimpleName());
		}

	}

}
