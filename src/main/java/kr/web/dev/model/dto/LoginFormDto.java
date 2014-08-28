package kr.web.dev.model.dto;

import kr.web.dev.model.CommonBean;

public class LoginFormDto extends CommonBean {
	String	username;
	String	password;
	String	successLogin;
	String	token;
	
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
	
	public String getSuccessLogin() {
		return successLogin;
	}
	
	public void setSuccessLogin(String successLogin) {
		this.successLogin = successLogin;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "LoginFormDto [username=" + username + ", password=" + password + ", successLogin="
				+ successLogin + ", token=" + token + ", errorCode=" + super.getErrorCode()
				+ ", errorMessage=" + super.getErrorMessage() + "]";
	}
	
}
