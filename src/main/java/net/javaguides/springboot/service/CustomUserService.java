package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserDetailsRepository;
import net.javaguides.springboot.repository.UserRepository;



@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	UserDetailsRepository userdetailrepository;
	
	 @Autowired
	 private UserRepository userRepository;


	
	Logger logger = LogManager.getLogger(CustomUserService.class);
	
	//To Get Particular Username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userdetailrepository.findByUsername(username);
		
			
		if(null==user) {
			throw new UsernameNotFoundException("User Not Found With username"+username);
		}
		return user;
		
	}
	
	public User newUser(User user) {
		return userdetailrepository.save(user);
	}
	

	
	public CustomUserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				registrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}


}