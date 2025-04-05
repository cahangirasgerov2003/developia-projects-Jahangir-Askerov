package az.developia.librarian_jahangir_askerov.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterRequest {
	@NotNull(message = "{field.empty}")
	private String name;

	@NotNull(message = "{field.empty}")
	private String author;

	@NotNull(message = "{field.empty}")
	private String priceMin;

	@NotNull(message = "{field.empty}")
	private String priceMax;

	@NotNull(message = "{field.empty}")
	private String publishDate;
}
