package me.learnjava.domain;

import java.io.Serializable;
import java.util.Date;

//дget��set��ʱ����ÿ�ݼ�alt + / ��������

//�û�ʵ����
public class User implements Serializable {

	private static final long serialVersionUID = -4444394504494160486L;
	
	//�û�ID
	private String id;
	//�û���
	private String userName;
	//�û�����
	private String userPwd;
	//�û�����
	private String email;
	//�û�����
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
