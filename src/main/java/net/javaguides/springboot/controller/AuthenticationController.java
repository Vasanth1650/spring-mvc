package net.javaguides.springboot.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.config.JwtTokenHelper;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.response.AuthenticationRequest;
import net.javaguides.springboot.response.LoginResponse;
import net.javaguides.springboot.response.UserInfo;



@RestController

@CrossOrigin
public class AuthenticationController {
	

	
	    

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest)  {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<UserInfo> getUserInfo(Principal user){
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userObj.getId());
		userInfo.setUsername(userObj.getUsername());
		userInfo.setEmail(userObj.getEmail());
		userInfo.setPhonenumber(userObj.getPhonenumber());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		return ResponseEntity.ok(userInfo);	
		
	}
	
	
}