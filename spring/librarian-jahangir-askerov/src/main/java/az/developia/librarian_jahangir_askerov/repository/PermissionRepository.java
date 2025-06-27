package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.librarian_jahangir_askerov.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

//	Method query
	public abstract List<PermissionEntity> findAllByAuthorityContaining(String name);

	public abstract Boolean existsByAuthority(String authorityName);

//	Native query
	@Query(value = "Select * from permissions limit ?1, ?2", nativeQuery = true)
	public abstract List<PermissionEntity> findAllPaginated(Integer page, Integer size);

}
