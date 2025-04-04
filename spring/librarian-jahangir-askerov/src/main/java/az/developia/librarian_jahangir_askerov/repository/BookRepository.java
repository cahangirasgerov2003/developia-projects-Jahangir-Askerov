package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.BookEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
//	Query method
//	public abstract List<BookEntity> findAllByNameContaining(String name);

//	Native query
	@Query(value = "Select * from books limit ?1, ?2", nativeQuery = true)
	public abstract List<BookEntity> getPaginated(Integer page, Integer size);

	@Query(value = "Select * from books where  operator_id=?2 and lower(name) like %?1%", nativeQuery = true)
	public abstract List<BookEntity> findAllByNameContaining(String name, Integer operator_id);
}
