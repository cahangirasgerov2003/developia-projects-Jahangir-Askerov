package az.developia.librarian_jahangir_askerov.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookSingleResponse {

	private Integer id;

	private Integer book_id;

	private Integer student_id;

	private Integer librarian_id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate borrowDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate returnDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate actualReturnDate;

	private LendingStatus status;

	private BookCondition condition;

	private BigDecimal fineAmount;

	private String note;

}
