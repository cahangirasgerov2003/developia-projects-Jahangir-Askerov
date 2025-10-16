package az.developia.librarian_jahangir_askerov.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.Immutable;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "borrowed_books_details")
@Entity
@Immutable
public class BorrowedBookEntity {
	@Id
	private Integer id;

	private Integer student_id;

	private Integer book_id;

	private LocalDate borrowDate;

	private LocalDate returnDate;

	private LocalDate actualReturnDate;

	@Enumerated(value = EnumType.STRING)
	private LendingStatus status;

	@Enumerated(EnumType.STRING)
	private BookCondition condition;

	@Enumerated(EnumType.STRING)
	private BookCondition returnCondition;

	private BigDecimal fineAmount;

	private String note;

	private String bookName;

	private Integer librarianIdentity;

	private String librarianName;

	private String studentName;

	private String studentSurname;

	private String studentEmail;

	private String studentPhone;

	private String country;

	private String city;
}
