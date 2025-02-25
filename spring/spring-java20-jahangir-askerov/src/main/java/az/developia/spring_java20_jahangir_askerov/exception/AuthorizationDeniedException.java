package az.developia.spring_java20_jahangir_askerov.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthorizationDeniedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationDeniedException(String message) {
		super(message);
	}
}
