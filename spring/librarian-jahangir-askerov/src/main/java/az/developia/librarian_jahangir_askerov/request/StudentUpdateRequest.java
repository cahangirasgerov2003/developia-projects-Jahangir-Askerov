package az.developia.librarian_jahangir_askerov.request;

import az.developia.librarian_jahangir_askerov.model.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateRequest {

	@NotBlank(message = "{field.empty}")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{email.invalid.format}")
	private String email;

	@NotBlank(message = "{field.empty}")
	@Pattern(regexp = "^(050|051|055|070|077)-\\d{3}-\\d{2}-\\d{2}$", message = "{phone.invalid.format}")
	private String phone;

	@Valid
	@Embedded
	@NotNull(message = "{field.empty}")
	private Address address;

}
