package az.developia.librarian_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianBookCountSingleResponse {

	private Integer id;

	private String name;

	private String surname;

	private String username;

	private Integer book_count;

}
