package az.developia.spring_java20_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.spring_java20_jahangir_askerov.entity.SellerEntity;
import jakarta.transaction.Transactional;

@Transactional
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {
//	Query method
	public abstract List<SellerEntity> findAllByNameContaining(String query);

//	Native query
	@Query(value = "Select * from sellers limit ?1, ?2", nativeQuery = true)
	public abstract List<SellerEntity> getPaginated(Integer page, Integer size);
}
