package az.developia.librarian_jahangir_askerov.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
	@NotBlank(message = "{field.empty}")
	@Size(min = 3, max = 100, message = "{field.length.invalid}")
	private String country;

	@NotBlank(message = "{field.empty}")
	@Size(min = 2, max = 100, message = "{field.length.invalid}")
	private String city;

	@NotBlank(message = "{field.empty}")
	@Size(min = 3, max = 100, message = "{field.length.invalid}")
	private String street;

	@NotBlank(message = "{field.empty}")
	@Pattern(regexp = "\\d{5}", message = "{zip.code.invalid.format}")
	private String zipCode;
}
