package az.developia.spring_java20_jahangir_askerov.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

}
