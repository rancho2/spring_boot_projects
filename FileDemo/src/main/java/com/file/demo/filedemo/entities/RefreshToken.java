package com.file.demo.filedemo.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="refresh_tokens")
public class RefreshToken {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String tokenId;
	private String refreshToken;
	private Instant expiry;
	@OneToOne
	private User user;
	public RefreshToken(String tokenId, String refreshToken, Instant expiry, User user) {
		super();
		this.tokenId = tokenId;
		this.refreshToken = refreshToken;
		this.expiry = expiry;
		this.user = user;
	}
	public RefreshToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Instant getExpiry() {
		return expiry;
	}
	public void setExpiry(Instant expiry) {
		this.expiry = expiry;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "RefreshToken [tokenId=" + tokenId + ", refreshToken=" + refreshToken + ", expiry=" + expiry + ", user="
				+ user + "]";
	}
	
}
