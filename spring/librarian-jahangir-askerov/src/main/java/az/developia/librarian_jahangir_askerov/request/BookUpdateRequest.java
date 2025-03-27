package az.developia.librarian_jahangir_askerov.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {
	@NotNull(message = "{field.empty}")
	@Positive(message = "The price of the book cannot be negative")
	@Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "{price.fraction.invalid}")
	private BigDecimal price;

	@NotBlank(message = "{field.empty}")
	private String description;
}
