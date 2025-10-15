package az.developia.librarian_jahangir_askerov.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_lendings")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer book_id;

	private Integer student_id;

	private Integer librarian_id;

	private LocalDate borrowDate;

	private LocalDate returnDate;

	private LocalDate actualReturnDate;

	@Enumerated(value = EnumType.STRING)
	private LendingStatus status;

	@Enumerated(EnumType.STRING)
	private BookCondition condition;

	private BigDecimal fineAmount;

	private String note;

}
