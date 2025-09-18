package az.developia.librarian_jahangir_askerov.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.UserEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//	Query method
	public abstract boolean existsByUsername(String username);

	public abstract Optional<UserEntity> findByUsername(String username);

}
