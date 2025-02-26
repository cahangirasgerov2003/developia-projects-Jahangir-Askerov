package az.developia.spring_java20_jahangir_askerov.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyExceptionResponse {
	private String message;
	private LocalDate errorOccurrenceTime;
}
