package az.developia.librarian_jahangir_askerov.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionAddRequest {

	@Pattern(regexp = "^ROLE(?:_[A-Z]+)+$", message = "{permission.invalid.format}")
	private String authority;

	@Min(value = 0, message = "Minimum value must be 0")
	@Max(value = 1, message = "Maximum value must be 1")
	private Integer librarian;

	private Integer admin;
}
