package az.developia.librarian_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.librarian_jahangir_askerov.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
