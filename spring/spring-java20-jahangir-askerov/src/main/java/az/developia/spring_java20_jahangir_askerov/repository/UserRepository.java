package az.developia.spring_java20_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.spring_java20_jahangir_askerov.entity.UserEntity;
import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
// Method query
	public abstract Boolean existsByUsername(String username);

}
