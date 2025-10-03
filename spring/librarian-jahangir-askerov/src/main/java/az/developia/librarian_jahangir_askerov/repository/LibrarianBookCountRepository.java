package az.developia.librarian_jahangir_askerov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LibrarianBookCountEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional(rollbackOn = Exception.class)
public interface LibrarianBookCountRepository extends JpaRepository<LibrarianBookCountEntity, Integer> {

}
