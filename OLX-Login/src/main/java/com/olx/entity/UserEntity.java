package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int id;
	  
	  @Column(name = "firstname")
	  private String firstName;
	  
	  @Column(name = "lastname")
	  private String lastName;
	  
	  @Column(name = "username")
	  private String username;
		
      @Column(name = "password")
      private String password;
      
      @Column(name = "active")
  	  private boolean active;
      
      @Column(name = "roles")
  	  private String roles;
      private String email;
 	  private long phone;

	public UserEntity() {
		super();
	}



	public UserEntity(int id, String firstName, String lastName, String username, String password, boolean active,
			String roles, String email, long phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.email = email;
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getPhone() {
		return phone;
	}



	public void setPhone(long phone) {
		this.phone = phone;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
      
      

}
