package az.developia.spring_java20_jahangir_askerov.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "books")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "The name part of the book cannot be empty")
	@Size(min = 3, max = 80, message = "The name of the book can contain a minimum of 3 and a maximum of 80 characters")
	private String name;

	@NotNull(message = "The author part of the book cannot be empty")
	@Size(min = 2, max = 40, message = "The author of the book can contain a minimum of 2 and a maximum of 40 characters")
	private String author;

	@NotNull(message = "The price part of the book cannot be empty")
	@PositiveOrZero(message = "The price of the book cannot be negative")
	@Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "The fractional part for the price of the book can be a maximum of 2 digits")
	private BigDecimal price;

	@NotBlank(message = "The description part of the book cannot be empty")
	private String description;

	private String color;

	@NotNull(message = "The page count part of the book cannot be empty")
	@Min(value = 10, message = "The book should contain at least 10 pages")
	private Integer pageCount;

	@Past
	private LocalDateTime publishDate;

	@PrePersist
	public void setDefaultColor() {
		if (this.getColor() == null || this.getColor().isBlank()) {
			this.setColor("White");
		}
	}
}
