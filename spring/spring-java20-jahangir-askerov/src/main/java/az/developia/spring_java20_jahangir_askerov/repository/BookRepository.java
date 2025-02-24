package az.developia.spring_java20_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.entity.BookEntity;

@RestController
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
//	Query method
	public abstract List<BookEntity> findAllByNameContaining(String name);

//	Native query
	@Query(value = "Select * from books limit ?1, ?2", nativeQuery = true)
	public abstract List<BookEntity> getPaginated(Integer page, Integer size);
}
