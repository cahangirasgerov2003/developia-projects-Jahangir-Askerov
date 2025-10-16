package az.developia.librarian_jahangir_askerov.request;

import az.developia.librarian_jahangir_askerov.enums.BookCondition;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;
import az.developia.librarian_jahangir_askerov.validation.NotNullField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookUpdateRequest {

	@NotNullField(fieldName = "Status")
	private LendingStatus status;

	@NotNullField(fieldName = "Condition")
	private BookCondition returnCondition;

}
