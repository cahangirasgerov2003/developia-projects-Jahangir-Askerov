package az.developia.librarian_jahangir_askerov.response;

import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianSingleResponse {

	private Integer id;

	private String name;

	private String surname;

	private String email;

	private String phone;

	private LocalDate birthday;

	private Address address;

	private Integer user_id;

}
