package az.developia.spring_java20_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.spring_java20_jahangir_askerov.model.SellerEntity;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {
	public abstract List<SellerEntity> findAllByNameContaining(String query);
}
