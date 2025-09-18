package az.developia.librarian_jahangir_askerov.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.TranslationEntity;
import az.developia.librarian_jahangir_askerov.repository.TranslationRepository;

@RestController
public class TranslationService {

	@Autowired
	private TranslationRepository repository;

	public Map<String, String> getByLanguage(String language) {
		List<TranslationEntity> allTranslations = repository.findAllByLanguage(language);

		Map<String, String> myMap = allTranslations.stream()
				.collect(Collectors.toMap(TranslationEntity::getWord, TranslationEntity::getTranslate));

		return myMap;
	}

}
