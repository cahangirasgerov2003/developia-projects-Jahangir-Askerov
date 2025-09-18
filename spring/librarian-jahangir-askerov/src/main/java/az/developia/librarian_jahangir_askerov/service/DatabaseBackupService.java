package az.developia.librarian_jahangir_askerov.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.librarian_jahangir_askerov.config.ApplicationConfig;

@Service
public class DatabaseBackupService {

	@Autowired
	private ApplicationConfig config;

	// Backup fayllarının saxlanacağı qovluq
	private final String BACKUP_FOLDER = "C:\\Users\\Cahangir\\Desktop\\BackUp";

	public void backupDatabase() {
		try {
			// Timestamp ilə unikal backup faylı
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			String backupFile = BACKUP_FOLDER + "\\backup_" + timestamp + ".sql";

			// Backup qovluğu yoxlanılır, yoxdursa yaradılır
			File folder = new File(BACKUP_FOLDER);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// Mysqldump komandasını hazırlamaq
			List<String> command = Arrays.asList("mysqldump", "-u" + config.getDbUser(), "-p" + config.getDbPassword(),
					config.getDbName());

			ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectOutput(new File(backupFile)); // Nəticəni fayla yaz
			pb.redirectErrorStream(true); // Hər iki stream birləşdirilir

			// Prosesin icrası
			Process process = pb.start();
			int processComplete = process.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup uğurla alındı: " + backupFile);
			} else {
				System.err.println("Backup alınarkən xəta baş verdi. Exit code: " + processComplete);
			}

		} catch (IOException e) {
			System.err.println("I/O xəta: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println("Proses gözlənərkən dayandırıldı: " + e.getMessage());
			Thread.currentThread().interrupt(); // Interrupted status-u saxla
		}
	}
}
