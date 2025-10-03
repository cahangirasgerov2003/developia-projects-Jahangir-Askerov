package az.developia.librarian_jahangir_askerov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "librarians_book_count")
@Entity
public class LibrarianBookCountEntity {

	@Id
	private Integer id;

	private String name;

	private String surname;

	private String username;

	private Integer book_count;

}
