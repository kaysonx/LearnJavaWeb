package me.utlight.struts;

import com.opensymphony.xwork2.ActionSupport;

import me.utlight.bean.User;

public class ValidateAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String execute(){
		return "success";
	}
	
}
