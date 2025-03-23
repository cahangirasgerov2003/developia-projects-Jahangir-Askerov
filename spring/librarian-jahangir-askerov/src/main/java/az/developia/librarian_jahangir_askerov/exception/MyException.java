package az.developia.librarian_jahangir_askerov.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BindingResult br;
	private String type;

	public MyException(String message, BindingResult br, String type) {
		super(message);
		this.br = br;
		this.type = type;
	}

}
