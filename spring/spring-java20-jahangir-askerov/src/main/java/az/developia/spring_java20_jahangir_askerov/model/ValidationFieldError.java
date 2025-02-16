package az.developia.spring_java20_jahangir_askerov.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationFieldError {
	String defaultMessage;
	String field;

	public ValidationFieldError(String field, String defaultMessage) {
		this.field = field;
		this.defaultMessage = defaultMessage;
	}
}
