package az.developia.librarian_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianAddResponse {
	private Integer id;
	private Integer user_id;
	private Integer affectedRows;
}
