package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.librarian_jahangir_askerov.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

//	Native query
	@Query( value = "Select count(*) from students where operator_id=?1 and lower(name) like %?2% and lower(surname) like %?3%" ,nativeQuery = true)
	Long getByFilterCount(Integer operator_id, String name, String surname);

	@Query( value = "Select * from students where operator_id=?1 and lower(name) like %?2% and lower(surname) like %?3% limit ?4, ?5" ,nativeQuery = true)
	List<StudentEntity> getByFilter(Integer operator_id, String name, String surname, Integer page, Integer size);

}
