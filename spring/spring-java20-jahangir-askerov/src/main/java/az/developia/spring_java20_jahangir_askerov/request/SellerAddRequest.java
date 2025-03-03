package az.developia.spring_java20_jahangir_askerov.request;

import java.time.LocalDate;

import az.developia.spring_java20_jahangir_askerov.model.Address;
import az.developia.spring_java20_jahangir_askerov.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerAddRequest {
	@NotNull(message = "{field.empty}")
	@Size(min = 3, max = 40, message = "{field.length.invalid}")
	private String name;

	@NotNull(message = "{field.empty}")
	@Size(min = 3, max = 40, message = "{field.length.invalid}")
	private String surname;

	@NotNull(message = "{field.empty}")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
	private String email;

	@NotNull(message = "{field.empty}")
	@Past(message = "{user.birthday}")
	private LocalDate birthday;

	@Valid
	@Embedded
	@NotNull(message = "{field.empty}")
	private Address address;

	@NotNull(message = "{field.empty}")
	@Size(min = 2, max = 40, message = "{field.length.invalid}")
	@Column(unique = true, nullable = false)
	private String username;

	@ValidPassword
	private String password;
}
