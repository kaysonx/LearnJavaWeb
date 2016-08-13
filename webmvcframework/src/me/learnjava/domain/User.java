package me.learnjava.domain;

import java.io.Serializable;
import java.util.Date;

//写get和set的时候可用快捷键alt + / 快速生成

//用户实体类
public class User implements Serializable {

	private static final long serialVersionUID = -4444394504494160486L;
	
	//用户ID
	private String id;
	//用户名
	private String userName;
	//用户密码
	private String userPwd;
	//用户邮箱
	private String email;
	//用户生日
	private Date birthday;
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public String getId() {
		return id;
	}
		
}
