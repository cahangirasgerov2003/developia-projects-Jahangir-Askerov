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

	@Query(value = "Select count(*) from books where operator_id=?1 and lower(author) like %?2% and lower(name) like %?3% and (price between ?4 and ?5) and publish_date like %?6%", nativeQuery = true)
	public abstract Long getByFilterCount(Integer operator_id, String author, String name, String priceMin,
			String priceMax, String publishDate);

	@Query(value = "Select * from books where operator_id=?1 and lower(author) like %?2% and lower(name) like %?3% and (price between ?4 and ?5) and publish_date like %?6%", nativeQuery = true)
	public abstract List<BookEntity> getByFilter(Integer operator_id, String author, String name, String priceMin,
			String priceMax, String publishDate);

	@Query(value = "Select count(*) from books where (?1=0 or category_id=?1) and lower(name) like %?2%", nativeQuery = true)
	public abstract Long getByCustomerFilterCount(Integer categoryId, String name);

	@Query(value = "Select * from books where (?1=0 or category_id=?1) and lower(name) like %?2% limit ?3, ?4", nativeQuery = true)
	public abstract List<BookEntity> getByCustomerFilter(Integer categoryId, String name, Integer page, Integer size);
}
