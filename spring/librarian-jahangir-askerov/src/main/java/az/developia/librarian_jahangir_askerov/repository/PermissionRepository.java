package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.RoleEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface PermissionRepository extends JpaRepository<RoleEntity, Integer> {

//	Query method
	public abstract List<RoleEntity> findAllByAuthorityContaining(String name);

	public abstract Boolean existsByAuthority(String authorityName);

//	Native query
	@Query(value = "Select * from permissions limit ?1, ?2", nativeQuery = true)
	public abstract List<RoleEntity> findAllPaginated(Integer page, Integer size);

}
