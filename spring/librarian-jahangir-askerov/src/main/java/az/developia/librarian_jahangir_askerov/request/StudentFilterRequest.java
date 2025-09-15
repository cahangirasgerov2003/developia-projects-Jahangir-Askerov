package az.developia.librarian_jahangir_askerov.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFilterRequest {
	@NotBlank(message = "{field.empty}")
	@Size(min = 0, max = 40, message = "{field.length.invalid}")
	private String name;

	@NotBlank(message = "{field.empty}")
	@Size(min = 0, max = 40, message = "{field.length.invalid}")
	private String surname;
}
