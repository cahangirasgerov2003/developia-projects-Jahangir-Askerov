package az.developia.librarian_jahangir_askerov.response;

import java.time.LocalDateTime;
import java.util.List;

import az.developia.librarian_jahangir_askerov.model.ValidationFieldError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyExceptionResponse {
	private List<ValidationFieldError> fieldErrors;
	private String message;
	private String type;
	private LocalDateTime errorOccurrenceTime;
}
