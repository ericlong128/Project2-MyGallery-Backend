package com.revature.service;

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
import com.revature.exception.AuthenticationException;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Artwork;
import com.revature.model.User;

@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ArtworkRepository artworkRepo;
	
	public User authenticate(User user) {
		
		User userInDb = userRepo.findByUsername(user.getUsername())
				.orElseThrow(AuthenticationException::new);
		
		if (user.getPassword().equals(userInDb.getPassword())) {
			return userInDb;
		}
		
		throw new AuthenticationException();
	}
	
	@Transactional(readOnly=true) // make sure method fires against database in one unit
	public Set<User> findAll() {
		
		return userRepo.findAll().stream()
				.collect(Collectors.toSet());
	}
	
	 
	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
	public User add(User u) {
		return userRepo.save(u);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) // default setting
	public void remove(int id) {
		userRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
	}
	
	@Transactional(readOnly=true)
	public User getById(int id) {
		if (id <= 0) {
			log.warn("ID cannot be <= 0. ID passed was: {}", id);
			return null;
		} else {
			return userRepo.getById(id);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
	public User updateUser(int id, User updateU) {
			User u = userRepo.getById(id);

			if(!u.getFirstName().equals(updateU.getFirstName())) {
				u.setFirstName(updateU.getFirstName());
			}
			if(!u.getLastName().equals(updateU.getLastName())) {
				u.setLastName(updateU.getLastName());
			}
			if(!u.getUsername().equals(updateU.getUsername())) {
				u.setUsername(updateU.getUsername());
			}
			if(!u.getPassword().equals(updateU.getPassword())) {
				u.setPassword(updateU.getPassword());
			}
			if(!u.getEmail().equals(updateU.getEmail())) {
				u.setEmail(updateU.getEmail());
			}
			return u;
	}
	

//	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
//	public User addArtworkToUser(Artwork art, User u) {
//		userRepo.joinArtworkToUser(art.getId(), u.getId());
//		return userRepo.save(u);
//	}
}
