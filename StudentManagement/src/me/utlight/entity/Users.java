package me.utlight.entity;

public class Users {
	
	private int uid;
	private String username;
	private String password;
	
	public Users() {
		
	}
	
	
	public Users(int uid, String username, String password) {
		
		this.uid = uid;
		this.username = username;
		this.password = password;
	}



	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + "]";
	}


	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
}
