package az.developia.spring_java20_jahangir_askerov.model;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerUpdateRequest {
	@NotNull(message = "The address part of the seller cannot be empty")
	@Valid
	@Embedded
	private Address address;

	@NotNull(message = "The username part of the seller cannot be empty")
	private String username;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&()_+=^,.;:/<>|\\-])[A-Za-z\\d@$!%*?&()_+=^,.;:/<>|\\-]{8,20}$", message = "The password format is incorrect")
	private String password;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The email format is incorrect, please check and try again")
	private String email;
}
