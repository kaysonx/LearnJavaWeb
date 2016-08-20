package me.utlight.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import me.utlight.entity.Users;
import me.utlight.service.UsersDAO;
import me.utlight.serviceimpl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	private static final long serialVersionUID = 1L;
	//采用模型驱动方式获取表单内容  必须初始化
	private Users user = new Users();
	
	
	public String login(){
		UsersDAO udao = new UsersDAOImpl();
		if (udao.usersLogin(user)) {
			this.session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}else {
			return "login_failure";
		}
	}
	
	@SkipValidation
	public String logout(){
		if(this.session.getAttribute("loginUserName")!= null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	//默认对所有方法起作用
	@Override
	public void validate() {
		if ("".equals(user.getUsername().trim())) {
			this.addFieldError("usernameError", "用户名不能为空!");
		}
		if (user.getPassword().length() < 3) {
			this.addFieldError("userpassword", "密码长度不能少于3位!");
		}
	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
