package com.revature.data;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.Artwork;
import com.revature.model.User;

@Repository
public interface ArtworkRepository extends JpaRepository <Artwork, Integer> {

//	Set<Artwork> findByOwnersUsername(String username);
//	Set<Artwork> findByOwnersByOwnerId(int id);
	
	Artwork findById(int id);
	
//	@Modifying
//	@Query(
//	  value = 
//	    "insert into users_artworks (user_id, artwork_id) values (:userId, :artworkId)",
//	  nativeQuery = true)
//	void joinUserArtwork(@Param("userId") Integer us, @Param("artworkId") Integer ar);

}
