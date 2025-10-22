package az.developia.librarian_jahangir_askerov.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import az.developia.librarian_jahangir_askerov.validation.NotNullField;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendingRequest {

	@NotNullField(fieldName = "Book id")
	@Positive(message = "Student id cannot be negative or zero")
	private Integer book_id;

	@NotNullField(fieldName = "Student id")
	@Positive(message = "Student id cannot be negative or zero")
	private Integer student_id;

	@NotNullField(fieldName = "Borrow date")
	private LocalDate borrowDate;

	@NotNullField(fieldName = "Return date")
	@Future(message = "Return date must be in the past")
	private LocalDate returnDate;

	@NotNullField(fieldName = "Fine amount")
	@DecimalMin(value = "1.0", message = "{fineAmount.min}")
	@DecimalMax(value = "100.0", message = "{fineAmount.max}")
	private BigDecimal fineAmount;

	@NotNullField(fieldName = "Status")
	private LendingStatus status;

	@NotNullField(fieldName = "Condition")
	private BookCondition bookCondition;

	@NotBlank(message = "{field.empty}")
	private String note;

}
