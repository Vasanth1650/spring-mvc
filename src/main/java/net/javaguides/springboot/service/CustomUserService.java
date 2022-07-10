package net.javaguides.springboot.service;

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
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserDetailsRepository;



@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	UserDetailsRepository userdetailrepository;
	
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
	
	public Collection<? extends GrantedAuthority> finding(String username) {
		User user = userdetailrepository.findByUsername(username);
		
		return user.getAuthorities();
	}
	
	
	
	//findbyUsername Method
	public User getByUsername(String username) throws Exception {
		//checks if any username exists
		User user = userdetailrepository.findByUsername(username);
		try {
			if(user==null) {
				//if not exists throws exception and log4j error
				logger.error("No User Found.... "+username);
				throw new Exception("Catch me");
			}
			if(user!=null) {
				//if found shows the found value
				logger.info("Username Found..."+username);
			}
		}catch(Exception e){
			//catches exception
			logger.error("The Username Is Not Present... "+username);
			throw new Exception("The Username Is Not Present...");
		}
		return user;
	}
	
	
	//To Add New User
	public User addUser(User user) throws Exception {
		//getting values locally if username is foundby
		User local = this.userdetailrepository.findByUsername(user.getUsername());
		try {
			if(local != null) {
				//logger throws error message where the information of the user already exists and exception
				logger.error("User Already Exists");
				throw new Exception("Catch me");
			}else {
				//if not any saves the data
				logger.info("User Not Exists So Adding Them");
				//and saves in local variable
				local = userdetailrepository.save(user);
			}
		}
		catch(Exception e) {
			//catches any exception occurs
			throw new Exception("User Already Exists");
		}
		return local;
	}
	
	
	//To Get Particular User By Id
	public User getById(int id) {
		logger.warn("Getting Info Of The User :"+id);
		Optional<User> option = userdetailrepository.findById(id);
		return (option.get());
	}
	
	//To Get All Users
	public List<User> getUsers(){
		//Data breach may occur user data may be stolen
		logger.fatal("Values Are Getting For Security");
		List<User> user = userdetailrepository.findAll();
		return user;
	}
	
	//Update Method To Update User Information
	public User updateUser(int id,User user) {
		if(getById(user.getId())==null) {
			logger.error("Id Not Found");
			return null;
		}
		return userdetailrepository.save(user);
	}

}