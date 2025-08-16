package com.file.demo.filedemo.models;

public class JwtResponse {
	private String jwtToken;
	private String refreshToken;
	private String userName;
	public JwtResponse(String jwtToken, String refreshToken, String userName) {
		super();
		this.jwtToken = jwtToken;
		this.refreshToken = refreshToken;
		this.userName = userName;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + ", refreshToken=" + refreshToken + ", userName=" + userName + "]";
	}
}
