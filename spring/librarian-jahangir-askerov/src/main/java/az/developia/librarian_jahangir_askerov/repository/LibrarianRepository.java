package az.developia.librarian_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LibrarianEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public interface LibrarianRepository extends JpaRepository<LibrarianEntity, Integer> {

}
