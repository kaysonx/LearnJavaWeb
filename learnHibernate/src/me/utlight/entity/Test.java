package me.utlight.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

//测试使用注解的方式 配置Hibernate
@Entity
public class Test {
	
	private int id;
	private String username;
	private String password;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
