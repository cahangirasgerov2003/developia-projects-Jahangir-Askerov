package az.developia.spring_java20_jahangir_askerov.request;

import az.developia.spring_java20_jahangir_askerov.model.Address;
import az.developia.spring_java20_jahangir_askerov.validation.ValidPassword;
import jakarta.persistence.Column;
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
	@Valid
	@Embedded
	@NotNull(message = "{field.empty}")
	private Address address;

	@NotNull(message = "{field.empty}")
	@Column(unique = true)
	private String username;

	@ValidPassword
	private String password;

	@NotNull(message = "{field.empty}")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
	private String email;
}
