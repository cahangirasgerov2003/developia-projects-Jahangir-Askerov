package az.developia.librarian_jahangir_askerov.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAddRequest {
	@NotNull(message = "{field.empty}")
	@Size(min = 3, max = 80, message = "{field.length.invalid}")
	private String name;

	@NotNull(message = "{field.empty}")
	@Size(min = 2, max = 40, message = "{field.length.invalid}")
	private String author;

	@NotNull(message = "{field.empty}")
	@PositiveOrZero(message = "Price cannot be negative")
	@Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "{price.fraction.invalid}")
	private BigDecimal price;

	@NotBlank(message = "{field.empty}")
	private String description;

	private String color;

	@NotNull(message = "{field.empty}")
	@Min(value = 10, message = "{pages.min}")
	private Integer pageCount;

//	yyyy-MM-dd HH:mm:ss
	@NotNull(message = "{field.empty}")
	@Past(message = "Publish date must be in the past")
	private LocalDate publishDate;

	@PrePersist
	public void setDefaultColor() {
		if (this.getColor() == null || this.getColor().isBlank()) {
			this.setColor("White");
		}
	}
}
