package az.developia.librarian_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.developia.librarian_jahangir_askerov.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

//	Native query
	@Modifying
	@Query(value = "insert into authorities (username, authority) select ?1, authority from permissions where librarian = 1", nativeQuery = true)
	Integer addLibrarianAuthorities(String username);

}
