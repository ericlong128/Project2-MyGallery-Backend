package com.revature.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.ArtworkService;
import com.revature.util.HttpRequest;

@RestController
@RequestMapping("/artworks")
public class ArtController {
	
	@Autowired
	ArtworkService aservice;
	

	 @GetMapping
	    public ResponseEntity<?> getCountry() {
	        try {
	            HttpRequest request = HttpRequest
	                    .get("https://api.artic.edu/api/v1/artworks/")
	                    .connectTimeout(120000);
	            String res = request.body();
	            return new ResponseEntity<>(res, HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
			
		}
    
 
}
