package kr.web.dev.model.vo;

import java.io.Serializable;

import kr.web.dev.model.CommonBean;

public class AccountVO extends CommonBean implements Serializable {
	private static final long	serialVersionUID	= 8325859627946261279L;
	
	private int					seq;
	private String				username;
	private String				password;
	private String				role;
	private String				enabled;
	private String				mail;
	private String				sms;
	
	public int getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getSms() {
		return sms;
	}
	
	public void setSms(String sms) {
		this.sms = sms;
	}
	
	@Override
	public String toString() {
		return "AccountVO [seq=" + seq + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", enabled=" + enabled + ", mail=" + mail + ", sms=" + sms
				+ "]";
	}
	
}
