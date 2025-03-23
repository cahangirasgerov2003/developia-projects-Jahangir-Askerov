package az.developia.librarian_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionSingleResponse {
	private Integer id;

	private String authority;

	private Integer librarian;

	private Integer admin;
}
