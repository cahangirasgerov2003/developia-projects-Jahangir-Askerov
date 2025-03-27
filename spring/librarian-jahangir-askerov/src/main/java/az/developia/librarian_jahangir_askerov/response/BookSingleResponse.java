package az.developia.librarian_jahangir_askerov.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSingleResponse {
	private Integer id;

	private String name;

	private String author;

	private BigDecimal price;

	private String description;

	private String color;

	private Integer pageCount;

	private LocalDate publishDate;

}
