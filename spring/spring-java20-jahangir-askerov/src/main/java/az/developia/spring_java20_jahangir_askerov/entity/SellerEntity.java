package az.developia.spring_java20_jahangir_askerov.entity;

import java.time.LocalDate;

import az.developia.spring_java20_jahangir_askerov.model.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

	private String name;

	private String surname;

	private String email;

	private LocalDate birthday;

	private Address address;

	private Integer user_id;
}
