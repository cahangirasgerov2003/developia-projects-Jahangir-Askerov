package az.developia.spring_java20_jahangir_askerov.util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;
 
@Component
public class FileContentReader {

	public String readFromFile(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader("files/" + filePath))) {

			StringBuilder sb = new StringBuilder();
			String lineContent = br.readLine();

			while (!(lineContent == null)) {
				sb.append(lineContent);
				sb.append(System.lineSeparator());
				lineContent = br.readLine();
			}

			return sb.toString().replaceAll("\r\n", ", ").replaceAll("\n", ", ");

		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
