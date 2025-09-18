package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.TranslationEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface TranslationRepository extends JpaRepository<TranslationEntity, Integer> {

//	Method Query
	List<TranslationEntity> findAllByLanguage(String language);

}
