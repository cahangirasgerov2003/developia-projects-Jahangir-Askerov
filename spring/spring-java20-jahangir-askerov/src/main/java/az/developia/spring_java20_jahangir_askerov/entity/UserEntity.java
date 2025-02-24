package az.developia.spring_java20_jahangir_askerov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "/users")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	private String username;

	private String password;

	@Column(columnDefinition = "int default 1")
	private Integer enabled;
}
