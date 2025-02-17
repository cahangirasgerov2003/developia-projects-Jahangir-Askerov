package az.developia.spring_java20_jahangir_askerov.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotFoundExceptionResponse {
	private String message;
	private LocalDateTime errorOccurrenceTime;
}
