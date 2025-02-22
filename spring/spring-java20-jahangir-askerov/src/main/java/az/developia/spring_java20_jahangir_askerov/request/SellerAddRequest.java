package az.developia.spring_java20_jahangir_askerov.request;

import az.developia.spring_java20_jahangir_askerov.model.Address;
import az.developia.spring_java20_jahangir_askerov.validation.ValidPassword;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerAddRequest {
	@NotNull(message = "The name part of the seller cannot be empty")
	@Size(min = 3, max = 40, message = "The name of the seller can contain a minimum of 3 and a maximum of 40 characters")
	private String name;

	@NotNull(message = "The surname part of the seller cannot be empty")
	@Size(min = 3, max = 40, message = "The surname of the seller can contain a minimum of 3 and a maximum of 40 characters")
	private String surname;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The email format is incorrect, please check and try again")
	private String email;

	@NotNull(message = "The age part of the seller cannot be empty")
	@Min(value = 18, message = "You must be at least 18 years old to be able to sell a book")
	private Integer age;

	@NotNull(message = "The address part of the seller cannot be empty")
	@Valid
	@Embedded
	private Address address;

	@NotNull(message = "The username part of the seller cannot be empty")
	@Size(min = 2, max = 40, message = "The username of the seller can contain a minimum of 2 and a maximum of 40 characters")
	private String username;

	@ValidPassword
	private String password;
}
