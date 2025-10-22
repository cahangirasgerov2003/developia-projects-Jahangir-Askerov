package az.developia.librarian_jahangir_askerov.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import az.developia.librarian_jahangir_askerov.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookDetailsResponse {

	private Integer student_id;

	private Integer book_id;

	private LocalDate borrowDate;

	private LocalDate returnDate;

	private LocalDate actualReturnDate;

	private LendingStatus status;

	private BookCondition bookCondition;

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

	private Address address;
}
