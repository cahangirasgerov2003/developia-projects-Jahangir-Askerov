package az.developia.librarian_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.AuthorityEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

//	Native query
	@Modifying
	@Query(value = "insert into authorities (username, authority) select ?1, authority from permissions where librarian = 1", nativeQuery = true)
	Integer addLibrarianAuthorities(String username);

	@Modifying
	@Query(value = "insert into authorities (username, authority) select ?1, authority from permissions where student = 1", nativeQuery = true)
	Integer addStudentAuthorities(String username);

}
