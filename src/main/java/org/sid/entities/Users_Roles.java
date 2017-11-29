package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users_Roles implements Serializable{
	@Id @GeneratedValue
	private Long id_userrole;
	private String username;
	private String roles;
	public Users_Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users_Roles(String username, String roles) {
		super();
		this.username = username;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
