package az.developia.librarian_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddResponse {
	private Integer id;
	private Integer userId;
	private Integer affectedRows;
}
