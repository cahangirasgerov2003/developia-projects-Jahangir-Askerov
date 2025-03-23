package az.developia.librarian_jahangir_askerov.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import az.developia.librarian_jahangir_askerov.exception.MyException;

public class FileContentReader {

	public String readFromFile(String fileName) {

		try (BufferedReader br = new BufferedReader(new FileReader("files/" + fileName))) {

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
