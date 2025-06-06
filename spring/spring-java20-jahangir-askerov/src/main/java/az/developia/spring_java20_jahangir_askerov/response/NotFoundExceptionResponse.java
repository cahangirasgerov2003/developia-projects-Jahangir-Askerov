package az.developia.spring_java20_jahangir_askerov.response;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotFoundExceptionResponse {
	private String message;
	private LocalDate errorOccurrenceTime;
}
