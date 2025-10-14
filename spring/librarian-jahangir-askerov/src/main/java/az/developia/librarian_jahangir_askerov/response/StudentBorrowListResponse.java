package az.developia.librarian_jahangir_askerov.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBorrowListResponse {

	private List<StudentBorrowSingleResponse> booksOnLoan;
	private Long totalSize;

}
