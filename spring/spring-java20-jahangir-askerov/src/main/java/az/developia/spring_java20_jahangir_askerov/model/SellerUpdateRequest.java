package az.developia.spring_java20_jahangir_askerov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerUpdateRequest {
	private Address address;
	private String username;
	private String password;
}
