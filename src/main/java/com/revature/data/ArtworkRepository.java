package com.revature.data;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Artwork;
import com.revature.model.User;

@Repository
public interface ArtworkRepository extends JpaRepository <Artwork, Integer> {

//	Set<Artwork> findByOwnersUsername(String username);
//	Set<Artwork> findByOwnersByOwnerId(int id);
	
	Artwork findById(int id);

}
