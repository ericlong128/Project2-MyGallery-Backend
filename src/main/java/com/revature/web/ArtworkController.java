package com.revature.web;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Artwork;
import com.revature.model.User;
import com.revature.service.ArtworkService;

@RestController
@RequestMapping("/artworks") // available at http://localhost:5000/api/artworks
@CrossOrigin(origins="*", allowedHeaders="*")
public class ArtworkController {

	
	@Autowired
	private ArtworkService artworkServ;
	
	// clients sends a GET request to http://localhost:5000/api/users and retrieves all artworks
	@GetMapping
	public ResponseEntity<List<Artwork>> getAll() {
		// ResponseEntity allows you to edit parts of the HTTP response like the status
		return ResponseEntity.ok(artworkServ.findAll());
	}
	
	// ad Artwork -> accept POST request 
	@PostMapping("/add")
	public ResponseEntity<Artwork> addArtwork(@Valid @RequestBody Artwork art) {
										// @Valid enforces Validations set up
		
//	 User owner = new User();
//	 Set<User> owners = art.getOwners();
//	 
//	 Iterator<User> it = owners.iterator();
//	 if (it.hasNext()) {
//		 owner = it.next();
//		 art.getOwners().add(owner);
//	 }
		
		return ResponseEntity.ok(artworkServ.add(art));
//		return ResponseEntity.ok(artworkServ.add(art, owner));
		// AOP intercepts invalid response with ResponseEntity Handler
	}
	
	// Find Artwork by their id
	@GetMapping("/{id}") // Allows client to send request to http://localhost:5000/api/users/{id}
	public ResponseEntity<Artwork> findArtworkById(@PathVariable("id") int id) {
		return ResponseEntity.ok(artworkServ.getById(id));
	}

	// Find Artwork by owner id
//	@GetMapping("owner/{id}") // Allows client to send request to http://localhost:5000/api/artworks/owner/{id}
//	public ResponseEntity<List<Artwork>> findArtworkByOwnerId(@PathVariable("id") int id) {
//		return ResponseEntity.ok(artworkServ.getArtworkByOwnerId(id));
//	}

	// Delete/remove user by id
	@DeleteMapping("/{id}") // 
	public void removeArtwork(@PathVariable("id") int id) {
		artworkServ.remove(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Artwork> updateArtworkInfo(@PathVariable("id") int id, @Valid @RequestBody Artwork art) {
		return ResponseEntity.ok(artworkServ.updateArtwork(id, art));
	}
	
}
