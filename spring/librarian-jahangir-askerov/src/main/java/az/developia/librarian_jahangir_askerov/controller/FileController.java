package az.developia.librarian_jahangir_askerov.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/files")
public class FileController {

//	File upload
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestParam MultipartFile file) throws Exception {
		
		InputStream stream = file.getInputStream();
		
		String originalFilename = file.getOriginalFilename();
		
		int extensionSeparatorIndex = originalFilename.lastIndexOf(".");
		
		String filenameWithoutExtension = originalFilename.substring(0, extensionSeparatorIndex);
		
//		String filenameExtension = originalFilename.substring(extensionSeparatorIndex);
		
		String uuid = UUID.randomUUID().toString();
		
		String filenameNewVersion = originalFilename.replace(filenameWithoutExtension, uuid);
		
		Files.copy(stream, Paths.get("C:/files", filenameNewVersion), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
