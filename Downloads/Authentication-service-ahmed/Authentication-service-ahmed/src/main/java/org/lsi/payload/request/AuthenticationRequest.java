package org.lsi.payload.request;

import org.springframework.context.annotation.ComponentScan;

import lombok.Data;

@Data
public class AuthenticationRequest {

	
	private String username;
	private String password;
	private String email;
	private String image;
	private String description;
	private long phone;
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	
}
