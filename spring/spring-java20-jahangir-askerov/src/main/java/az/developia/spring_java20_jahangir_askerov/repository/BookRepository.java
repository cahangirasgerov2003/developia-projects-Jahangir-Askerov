package az.developia.spring_java20_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.model.BookEntity;

@RestController
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	public abstract List<BookEntity> findAllByNameContaining(String name);
}
