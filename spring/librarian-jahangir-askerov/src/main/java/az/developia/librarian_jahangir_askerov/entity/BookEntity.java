package az.developia.librarian_jahangir_askerov.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

	private String name;

	private String author;

	private BigDecimal price;

	private String description;

	private String color;

	private Integer pageCount;

	private LocalDate publishDate;

	private Integer operator_id;

	private Integer bookCategory;

}
