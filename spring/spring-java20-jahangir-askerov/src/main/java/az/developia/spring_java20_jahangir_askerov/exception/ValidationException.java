package az.developia.spring_java20_jahangir_askerov.exception;

import org.springframework.validation.BindingResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationException extends RuntimeException {
	private BindingResult br;

	public ValidationException(String message, BindingResult br) {
		super(message);
		this.br = br;
	}
}
