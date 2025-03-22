package az.developia.librarian_jahangir_askerov.entity;

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
@Table(name = "permissions")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String authority;

	private Integer librarian;
	
	@Column(columnDefinition = "int default 1")
	private Integer admin;
}
