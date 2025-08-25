package az.developia.librarian_jahangir_askerov.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime errorOccurrenceTime;
}
