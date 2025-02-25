package az.developia.spring_java20_jahangir_askerov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String password;

	@Column(columnDefinition = "int default 1")
	private Integer enabled;

	private String type;
}
