package az.developia.librarian_jahangir_askerov.util.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

import az.developia.librarian_jahangir_askerov.exception.MyException;

public class FileContentReader {

//	new FileReader(...) → yalnız diskdə olan real fayllar üçündür (production-da problem yaradır).
//
//	ClassPathResource + InputStreamReader → həm dev, həm də production-da işləyir (resurs faylları üçün ən yaxşı yol).

	public String readFromFile(String fileName) {

		ClassPathResource resource = new ClassPathResource("files/" + fileName);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

			StringBuilder sb = new StringBuilder();

			String lineContent = br.readLine();

			while (lineContent != null) {
				sb.append(lineContent);
				sb.append(System.lineSeparator());
				lineContent = br.readLine();
			}

			return sb.toString().replaceAll("\r\n", ", ").replaceAll("\n", ", ");

		} catch (FileNotFoundException e) {
			throw new MyException("File not found: " + fileName, null, e.getClass().getSimpleName());
		} catch (IOException e) {
			throw new MyException("Error reading file: " + fileName, null, e.getClass().getSimpleName());
		}

	}

}
