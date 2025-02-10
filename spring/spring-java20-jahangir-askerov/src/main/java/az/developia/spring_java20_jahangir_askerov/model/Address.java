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
	@NotNull(message = "The country part of the address cannot be empty")
	@Size(min = 3, max = 100, message = "The country name can contain a minimum of 3 and a maximum of 100 characters")
	private String country;

	@NotNull(message = "The city part of the address cannot be empty")
	@Size(min = 2, max = 100, message = "The city name can contain a minimum of 2 and a maximum of 100 characters")
	private String city;

	@NotNull(message = "The street part of the address cannot be empty")
	@Size(min = 3, max = 100, message = "The street name can contain a minimum of 3 and a maximum of 100 characters")
	private String street;

	@NotNull(message = "The zip code part of the address cannot be empty")
	@Pattern(regexp = "\\d{5}", message = "The zip code must be exactly 5 digits")
	private String zipCode;
}
