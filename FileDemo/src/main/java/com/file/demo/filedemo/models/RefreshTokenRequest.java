package com.file.demo.filedemo.models;

import java.util.Objects;

public class RefreshTokenRequest {
	private String refreshToken;

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public RefreshTokenRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "RefreshTokenRequest [refreshToken=" + refreshToken + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(refreshToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefreshTokenRequest other = (RefreshTokenRequest) obj;
		return Objects.equals(refreshToken, other.refreshToken);
	}
}
