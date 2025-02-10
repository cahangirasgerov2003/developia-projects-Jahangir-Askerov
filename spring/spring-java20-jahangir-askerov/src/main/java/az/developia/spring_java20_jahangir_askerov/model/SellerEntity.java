package az.developia.spring_java20_jahangir_askerov.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sellers")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "The name part of the seller cannot be empty")
	@Size(min = 3, max = 80, message = "The name of the seller can contain a minimum of 2 and a maximum of 40 characters")
	private String name;

	@NotNull(message = "The surname part of the seller cannot be empty")
	@Size(min = 3, max = 80, message = "The surname of the seller can contain a minimum of 2 and a maximum of 40 characters")
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
	private String username;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&()_+=^,.;:/<>|\\-])[A-Za-z\\d@$!%*?&()_+=^,.;:/<>|\\-]{8,20}$", message = "The password format is incorrect")
	private String password;
}
