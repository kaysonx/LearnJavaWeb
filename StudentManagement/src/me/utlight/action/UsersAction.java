package me.utlight.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import me.utlight.entity.Users;
import me.utlight.service.UsersDAO;
import me.utlight.serviceimpl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	private static final long serialVersionUID = 1L;
	//����ģ��������ʽ��ȡ������  �����ʼ��
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
	
	//Ĭ�϶����з���������
	@Override
	public void validate() {
		if ("".equals(user.getUsername().trim())) {
			this.addFieldError("usernameError", "�û�������Ϊ��!");
		}
		if (user.getPassword().length() < 3) {
			this.addFieldError("userpassword", "���볤�Ȳ�������3λ!");
		}
	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
