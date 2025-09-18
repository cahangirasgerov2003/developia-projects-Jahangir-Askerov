package az.developia.librarian_jahangir_askerov.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.service.TranslationService;

@RestController
@RequestMapping(path = "translations")
public class TranslationController {

	@Autowired
	private TranslationService service;

	@GetMapping
	public ResponseEntity<Map<String, String>> getByLanguage(
			@RequestHeader(value = "Accept-Language", defaultValue = "en") Locale locale) {

		String language = locale.getLanguage();

		return ResponseEntity.ok(service.getByLanguage(language));

	}

}
