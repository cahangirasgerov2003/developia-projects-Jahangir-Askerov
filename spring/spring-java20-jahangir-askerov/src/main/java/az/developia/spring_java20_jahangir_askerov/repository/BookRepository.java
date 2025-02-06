package az.developia.spring_java20_jahangir_askerov.repository;

import az.developia.spring_java20_jahangir_askerov.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BookRepository extends JpaRepository<Book, Integer> {
   public abstract List<Book> findAllByNameContaining(String name);
}
