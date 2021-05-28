package com.sapient.healthyreps.entity;

public class User {
	private int userId;
	private String userName;
	private String emailId;
	private String password;
	private Boolean isAdmin;
	private int reputation;

	public User() {

	}

	public User(int Uid, String UserName, String UserEmail, String Password) {
		this.userId = Uid;
		this.userName = UserName;
		this.emailId = UserEmail;
		this.password = Password;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int uid) {
		userId = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	@Override
	public String toString() {
		return "UserRegister [Uid=" + userId + ", UserName=" + userName + "]";
	}

	public void setEmailId(String userEmail) {
		emailId = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
