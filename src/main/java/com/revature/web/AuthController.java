package com.revature.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.JwtTokenManager;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenManager tokenManager;
	
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpServletResponse response) {
		
		user = userService.authenticate(user);
		
		if (user != null) {
			String token = tokenManager.issueToken(user);
			
			response.addHeader("portal-token", token);
			response.addHeader("Access-Control-Expose-Headers", "portal-token");
			response.setStatus(200);
			
			return user;
		} else {
			response.setStatus(401);
			return null;
			
		}
		
	}

}
