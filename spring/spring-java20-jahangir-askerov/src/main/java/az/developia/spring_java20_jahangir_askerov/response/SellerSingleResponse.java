package az.developia.spring_java20_jahangir_askerov.response;

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

	private Integer age;

	private Address address;

	private String username;

	private String password;
}
