package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.service.CacheInspectionService;

@RestController
@RequestMapping(path = "/caches")
public class CacheInspectionController {

	@Autowired
	private CacheInspectionService service;

	@GetMapping
	public ResponseEntity<?> printCacheContents(@RequestParam(value = "name", required = true) String cacheName) {

		service.printCacheContents(cacheName);

		return ResponseEntity.noContent().build();
	}

}
