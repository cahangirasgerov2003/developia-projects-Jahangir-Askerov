package az.developia.spring_java20_jahangir_askerov.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import az.developia.spring_java20_jahangir_askerov.exception.NotFoundException;

public class FileContentReader {

	public String readFromFile(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader("files/" + filePath))) {

			StringBuilder sb = new StringBuilder();
			String lineContent = br.readLine();

			while (lineContent != null) {
				sb.append(lineContent);
				sb.append(System.lineSeparator());
				lineContent = br.readLine();
			}

			return sb.toString().replaceAll("\r\n", ", ").replaceAll("\n", ", ");

		} catch (FileNotFoundException e) {
			throw new NotFoundException("File not found: " + filePath);
		} catch (IOException e) {
			throw new NotFoundException("Error reading file: " + filePath);
		}
	}

}
