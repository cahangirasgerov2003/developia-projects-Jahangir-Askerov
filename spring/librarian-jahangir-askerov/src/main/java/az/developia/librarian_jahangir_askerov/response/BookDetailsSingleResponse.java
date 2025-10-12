package az.developia.librarian_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookDetailsSingleResponse extends BookSingleResponse {

	private String nameLibrarian;

	private String surname;

	private String username;

}
