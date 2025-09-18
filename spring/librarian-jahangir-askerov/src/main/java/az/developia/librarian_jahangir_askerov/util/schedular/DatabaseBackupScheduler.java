package az.developia.librarian_jahangir_askerov.util.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import az.developia.librarian_jahangir_askerov.service.DatabaseBackupService;

@Component
public class DatabaseBackupScheduler {

	@Autowired
	DatabaseBackupService service;

	@Scheduled(cron = "0 0 15 * * ?", zone = "Asia/Baku")
	public void performDatabaseBackup() {

		service.backupDatabase();

	}

}
