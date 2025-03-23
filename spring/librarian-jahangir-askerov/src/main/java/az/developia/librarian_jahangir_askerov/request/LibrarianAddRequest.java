package az.developia.librarian_jahangir_askerov.request;

import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.model.Address;
import az.developia.librarian_jahangir_askerov.validation.ValidBirthday;
import az.developia.librarian_jahangir_askerov.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianAddRequest {
	
	@NotBlank(message = "{field.empty}")
	@Size(min = 3, max = 40, message = "{field.length.invalid}")
	private String name;

	@NotBlank(message = "{field.empty}")
	@Size(min = 3, max = 40, message = "{field.length.invalid}")
	private String surname;

	@NotBlank(message = "{field.empty}")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
	private String email;

	@NotBlank(message = "{field.empty}")
	private String phone;

	@ValidBirthday
	private LocalDate birthday;
 
	@Valid
	@Embedded
	@NotBlank(message = "{field.empty}")
	private Address address;
	
	@NotBlank(message = "{field.empty}")
	@Size(min = 2, max = 40, message = "{field.length.invalid}")
	@Column(unique = true)
	private String username;
	
	@ValidPassword
	private String password;
}
