package az.developia.spring_java20_jahangir_askerov.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "{field.empty}")
	@Size(min = 3, max = 100, message = "{field.length.invalid}")
	private String country;

	@NotNull(message = "{field.empty}")
	@Size(min = 2, max = 100, message = "{field.length.invalid}")
	private String city;

	@NotNull(message = "{field.empty}")
	@Size(min = 3, max = 100, message = "{field.length.invalid}")
	private String street;

	@NotNull(message = "{field.empty}")
	@Pattern(regexp = "\\d{5}", message = "{zip.code.invalid.format}")
	private String zipCode;
}
