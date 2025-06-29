package az.developia.librarian_jahangir_askerov.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse {
	private List<BookSingleResponse> books;
	private Long totalSize;
}
