package az.developia.spring_java20_jahangir_askerov.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import az.developia.spring_java20_jahangir_askerov.model.Book;

@RestController
public class Repository {

	@Autowired
	private DataSource dataSource;

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM books");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Double price = rs.getDouble("price");
				String result = String.format("""
						Id : %d
						Book name : %s
						Book price : %f
						""", id, name, price);
				System.out.println(result);
				Book book = new Book(id, name, price);
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

}
