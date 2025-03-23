package az.developia.librarian_jahangir_askerov.entity;

import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.model.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "librarians")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String surname;

	private String email;

	private String phone;

	private LocalDate birthday;

	private Address address;

	private Integer user_id;
}
