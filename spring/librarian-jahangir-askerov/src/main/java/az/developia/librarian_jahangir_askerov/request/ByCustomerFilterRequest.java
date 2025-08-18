package az.developia.librarian_jahangir_askerov.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByCustomerFilterRequest {
	
	@NotNull(message = "{field.empty}")
	private String name;

	@NotNull(message = "{field.empty}")
	@Min(value = 0, message = "{pagination.size.min}")
	private Integer categoryId;
	
	@NotNull(message = "{field.empty}")
	@Min(value = 1, message = "{pagination.size.min}")
	private Integer page;
	
	@NotNull(message = "{field.empty}")
	@Min(value = 1, message = "{pagination.size.min}")
	@Max(value = 3, message = "{pagination.size.max}" )
	private Integer size;
}
