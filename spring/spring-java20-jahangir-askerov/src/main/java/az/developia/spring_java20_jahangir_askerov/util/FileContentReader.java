package az.developia.spring_java20_jahangir_askerov.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;

@Component
public class FileContentReader {

	public String readFromFile(String filePath) {
		String fullPath = "files/" + filePath;
		try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {

			StringBuilder sb = new StringBuilder();
			String lineContent = br.readLine();

			while (lineContent != null) {
				sb.append(lineContent);
				sb.append(System.lineSeparator());
				lineContent = br.readLine();
			}

			return sb.toString().replaceAll("\r\n", ", ").replaceAll("\n", ", ");

		} catch (FileNotFoundException e) {
			throw new NotFoundException("Unable to locate the file: " + fullPath);
		} catch (IOException e) {
			throw new NotFoundException("An error occurred while reading the file: " + fullPath);
		}
	}

}
