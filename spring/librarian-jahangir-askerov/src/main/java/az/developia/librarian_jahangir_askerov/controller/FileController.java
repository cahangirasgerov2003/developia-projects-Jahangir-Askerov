package az.developia.librarian_jahangir_askerov.controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import az.developia.librarian_jahangir_askerov.exception.MyException;
import az.developia.librarian_jahangir_askerov.util.io.FileContentReader;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/files")
public class FileController {
	
	@Autowired
	private FileContentReader contentReader;
	
	@Autowired
	private ResourceLoader loader;
	
	private Path rootLoacation;
	
	@PostConstruct
	public void init() {
		rootLoacation = Paths.get("C:/files");
	}

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
	
	@GetMapping("/download/raw/{filename:.+}")
	public ResponseEntity<Resource> regularDownload(@PathVariable String filename){
		
		Resource file = getResourceByFilename(filename);
		
		if(file == null) {
			throw new MyException(contentReader.readFromFile("fileNotFound.txt"), null, "FileNotFoundException");
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"" + filename + "\"" ).body(file);
	}
 
	private Resource getResourceByFilename(String filename) {
		
		try {
			Path fullPath = getFileFullPath(filename);
			
			Resource resource = new UrlResource(fullPath.toUri());
				
			if(resource.isReadable() || resource.exists()) {
				return resource;
			}
			
		} catch (MalformedURLException e) {
			e.getStackTrace();
		}
		
		
		
		return null;
	}

	private Path getFileFullPath(String filename) {
		return rootLoacation.resolve(filename);
	}
	
	@GetMapping(path = "/video/{title}", produces = "video/mp4")
	public Mono<Resource> partialDownload(@PathVariable String title, @RequestHeader String range){
		System.out.println("Download range : " + range);
		return getVideo(title);
	}

	private Mono<Resource> getVideo(String title) {
		return Mono.fromSupplier(() -> loader.getResource("file:C:/files/" + title));
	}
	
}
