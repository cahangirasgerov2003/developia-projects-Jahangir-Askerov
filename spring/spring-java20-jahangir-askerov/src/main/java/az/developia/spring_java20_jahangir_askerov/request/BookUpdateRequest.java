package az.developia.spring_java20_jahangir_askerov.request;

import java.math.BigDecimal;

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
	@NotNull(message = "The price part of the book cannot be empty")
	@Positive(message = "The price of the book cannot be negative")
	private BigDecimal price;

	@NotBlank(message = "The description part of the book cannot be empty")
	private String description;
}
