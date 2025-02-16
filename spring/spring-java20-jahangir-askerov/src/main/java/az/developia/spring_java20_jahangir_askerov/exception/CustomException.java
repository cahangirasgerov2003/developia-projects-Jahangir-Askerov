package az.developia.spring_java20_jahangir_askerov.exception;

import org.springframework.validation.BindingResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {

	private BindingResult br;

	public CustomException(String message, BindingResult br) {
		super(message);
		this.br = br;
	}
}
