package com.file.demo.filedemo.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="user_table")
public class User implements UserDetails{
	@Id
	private String userId;
	private String name;
	private String email;
	private String password;
	private String about;
	@OneToOne(mappedBy = "user",cascade=CascadeType.ALL)
	@JsonIgnore
	private RefreshToken refreshToken;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public RefreshToken getRefreshToken() {
		return refreshToken;
	}


	public void setRefreshToken(RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}


	public User(String userId, String name, String email, String password, String about, RefreshToken refreshToken) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.refreshToken = refreshToken;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

}
