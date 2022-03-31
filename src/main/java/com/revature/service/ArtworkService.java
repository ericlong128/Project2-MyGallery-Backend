package com.revature.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ArtworkRepository;
import com.revature.data.UserRepository;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Artwork;
import com.revature.model.User;

@Service
public class ArtworkService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArtworkRepository artworkRepo;
	
	@Autowired
	private UserRepository userRepo;


	@Transactional(readOnly = true)
	public List<Artwork> findAll() {
		return artworkRepo.findAll().stream().collect(Collectors.toList());
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
	public Artwork add(Artwork art) {
		artworkRepo.save(art);
		
		 User owner = new User();
		 User u = new User();
		 
		 Set<User> owners = art.getOwners();
		 
		 Iterator<User> it = owners.iterator();
		 if (it.hasNext()) {
			 owner = it.next();
			 art.getOwners().add(owner);
		 }

		 u = userRepo.getById(owner.getId());

		 u.getArtworks().add(art);
		// artworkRepo.save(art);
		 userRepo.save(u);
		 
		return art;
	}


	@Transactional(propagation=Propagation.REQUIRED) // default setting
	public void remove(int id) {
		Artwork art = artworkRepo.getById(id);
		
		 User owner = new User();
		 
		 Set<User> owners = art.getOwners();
		 
		 Iterator<User> it = owners.iterator();
		 if (it.hasNext()) {
			 owner = it.next();
//			 art.getOwners().remove(owner);
		 }

		 User u = userRepo.getById(owner.getId());
		 u.getArtworks().remove(art);
		 userRepo.save(u);
		 artworkRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Artwork getById(int id) {
		if (id <= 0) {
			log.warn("ID cannot be <= 0. ID passed was: {}", id);
			return null;
		} else {
			return artworkRepo.getById(id);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
	public Artwork updateArtwork(int id, Artwork updateArt) {
			Artwork art = artworkRepo.getById(id);
			
			if(art.getArtic_id()!=updateArt.getArtic_id()) {
				art.setArtic_id(updateArt.getArtic_id());
			}
			
			if(!art.getImage_id().equals(updateArt.getImage_id())) {
				art.setImage_id(updateArt.getImage_id());
			}

			if(!art.getImage_config().equals(updateArt.getImage_config())) {
				art.setImage_config(updateArt.getImage_config());
			}

			if(!art.getTitle().equals(updateArt.getTitle())) {
				art.setTitle(updateArt.getTitle());
			}

			if(!art.getArtist().equals(updateArt.getArtist())) {
				art.setArtist(updateArt.getArtist());
			}

			if(!art.getOrigin().equals(updateArt.getOrigin())) {
				art.setOrigin(updateArt.getOrigin());
			}

			if(!art.getDate().equals(updateArt.getDate())) {
				art.setDate(updateArt.getDate());
			}

			if(!art.getDescription().equals(updateArt.getDescription())) {
				art.setDescription(updateArt.getDescription());
			}

			if(art.getWidth()!=updateArt.getWidth()) {
				art.setWidth(updateArt.getWidth());
			}

			if(art.getHeight()!=updateArt.getHeight()) {
				art.setHeight(updateArt.getHeight());
			}

			return art;
	}

}
