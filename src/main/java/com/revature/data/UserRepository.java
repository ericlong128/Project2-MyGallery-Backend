package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	List<User> findByOrderByLastName(); // returns all users sorted by last name

//	@Modifying
//	@Query(
//	  value = 
//	    "insert into users_artworks (user_id, artwork_id) values (:userId, :artworkId)",
//	  nativeQuery = true)
//	void joinArtworkToUser(@Param("userId") Integer us, @Param("artworkId") Integer ar);

	

}
