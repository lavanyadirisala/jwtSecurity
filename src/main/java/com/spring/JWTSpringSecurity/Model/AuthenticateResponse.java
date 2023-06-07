package com.spring.JWTSpringSecurity.Model;

public class AuthenticateResponse {
	private final String jwt;

	public AuthenticateResponse() {
		this.jwt = "";
		
	}

	public AuthenticateResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
