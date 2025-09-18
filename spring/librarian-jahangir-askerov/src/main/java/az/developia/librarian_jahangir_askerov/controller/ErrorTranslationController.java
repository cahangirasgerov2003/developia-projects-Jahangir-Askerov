package az.developia.librarian_jahangir_askerov.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.service.ErrorTranslationService;

@RestController
@RequestMapping(path = "/error-messages")
public class ErrorTranslationController {

	@Autowired
	private ErrorTranslationService service;

	@GetMapping(path = "/all")
	public ResponseEntity<Map<String, String>> getAllByLanguage(
			@RequestHeader(value = "Accept-Language", defaultValue = "en") Locale locale) {

		return ResponseEntity.ok(service.getAllByLanguage(locale));
	}
 
	@GetMapping("/by-word")
	public ResponseEntity<String> getByWord(
			@RequestHeader(value = "Accept-Language", defaultValue = "en") Locale locale,
			@RequestParam(value = "word", required = true) String word) {

		return ResponseEntity.ok(service.getByWord(locale, word));

	}

}
