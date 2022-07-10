package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="AUTH_USER_AUTHORITY")

public class Auth {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	
	private int authorities_id;
	
	//Getters and Setters
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getAuthorities_id() {
		return authorities_id;
	}
	
	public void setAuthorities_id(int authorities_id) {
		this.authorities_id = authorities_id;
	}
	
	
	public Auth(int user_id, int authorities_id) {
		super();
		this.user_id = user_id;
		this.authorities_id = authorities_id;
	}
	
	//Super Constructor To Refer To The Base Class
	public Auth() {
		super();
	}
	
	//To String Helps To Get A String Object Representing The Value In Number Object 
	@Override
	public String toString() {
		return "Auth [user_id=" + user_id + ", authorities_id=" + authorities_id + "]";
	}
	
	
}
	
	
	
	
