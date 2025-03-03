package az.developia.spring_java20_jahangir_askerov.response;

import java.time.LocalDate;

import az.developia.spring_java20_jahangir_askerov.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerSingleResponse {
	private Integer id;

	private String name;

	private String surname;

	private String email;

	private LocalDate birthday;

	private Address address;

	private String username;

	private String password;
}
