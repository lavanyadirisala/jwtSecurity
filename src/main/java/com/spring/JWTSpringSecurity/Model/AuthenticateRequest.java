package com.spring.JWTSpringSecurity.Model;

public class AuthenticateRequest {

	String userName;
	String passWord;
	
	public AuthenticateRequest() {
		super();
	}
	public AuthenticateRequest(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
