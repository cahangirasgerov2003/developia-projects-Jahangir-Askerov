package az.developia.spring_java20_jahangir_askerov.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
	private String country;
	private String city;
	private String street;
	private String zipCode;
}
