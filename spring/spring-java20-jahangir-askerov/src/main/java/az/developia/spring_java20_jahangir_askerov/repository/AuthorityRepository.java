package az.developia.spring_java20_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.developia.spring_java20_jahangir_askerov.entity.AuthorityEntity;
import jakarta.transaction.Transactional;

@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

//	Native query
	@Modifying
	@Query(value = "insert into authorities (username, authority) select ?1, authority from permissions where seller = 1", nativeQuery = true)
	public abstract int addSellerAuthorities(String username);

}
