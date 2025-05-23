package az.developia.spring_java20_jahangir_askerov.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerAddResponse {
	private Integer id;
	private UserAddResponse user_id;
	private AuthoritiesAddResponse affectedRows;
}
