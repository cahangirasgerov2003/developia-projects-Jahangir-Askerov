package az.developia.librarian_jahangir_askerov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationFieldError {
	private String field;
	private String message;
}
