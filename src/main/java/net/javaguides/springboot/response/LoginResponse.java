/**
 * Login Response Helps When The User Is Authenticated And Provide The Token Which Contains Particular User Information To The 
 * User 
 * @author Vasanth
 **/

package net.javaguides.springboot.response;

public class LoginResponse {
	
	
	private String token;

	
	//Getters Ans Setters
	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
