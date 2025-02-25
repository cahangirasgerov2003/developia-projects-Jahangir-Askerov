package az.developia.spring_java20_jahangir_askerov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	@NotNull(message = "Authority cannot be null !")
	@Column(nullable = false)
	private String authority;

}
