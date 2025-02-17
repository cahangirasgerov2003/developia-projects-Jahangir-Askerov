package az.developia.spring_java20_jahangir_askerov.response;

import java.time.LocalDateTime;
import java.util.List;

import az.developia.spring_java20_jahangir_askerov.model.ValidationFieldError;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationExceptionResponse {
	private String Message;
	private List<ValidationFieldError> fieldErrors;
	private LocalDateTime errorOccurrenceTime;
}
