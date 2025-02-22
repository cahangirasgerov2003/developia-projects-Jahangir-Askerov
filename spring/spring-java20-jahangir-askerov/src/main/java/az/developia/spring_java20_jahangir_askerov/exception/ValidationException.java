package az.developia.spring_java20_jahangir_askerov.exception;

import org.springframework.validation.BindingResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BindingResult br;

	public ValidationException(String message, BindingResult br) {
		super(message);
		this.br = br;
	}
}
