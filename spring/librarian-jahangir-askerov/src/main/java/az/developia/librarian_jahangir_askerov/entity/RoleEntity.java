package az.developia.librarian_jahangir_askerov.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "roles")

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	private String authority;

	private Integer student;

	private Integer librarian;

	@Column(columnDefinition = "int default 1")
	private Integer admin;

	@PrePersist
	public void prePersist() {
		if (this.admin == null) {
			this.admin = 1;
		}
	}

	@ToString.Exclude
	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> users = new HashSet<UserEntity>();

	@Override
	public String toString() {
		return "UserEntity{" + "id=" + id + ", authority='" + authority + '\'' + ", student=" + student + ", librarian="
				+ librarian + ", admin=" + admin + ", roles=" + users.stream().map(UserEntity::getUsername) + '}';
	}
}
