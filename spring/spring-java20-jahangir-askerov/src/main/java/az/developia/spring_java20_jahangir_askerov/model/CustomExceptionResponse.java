package az.developia.spring_java20_jahangir_askerov.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomExceptionResponse {
	private String Message;
	private List<CustomFieldError> fieldErrors;
	private LocalDateTime errorOccurrenceTime;
}
