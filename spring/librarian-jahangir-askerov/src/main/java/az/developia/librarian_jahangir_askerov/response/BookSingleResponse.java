package az.developia.librarian_jahangir_askerov.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDate publishDate;

}
