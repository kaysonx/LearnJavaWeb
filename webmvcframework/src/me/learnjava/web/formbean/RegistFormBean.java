package me.learnjava.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * @author liusha
 * 封装表单
 * 用于检验注册表单数据
 */

public class RegistFormBean {
	
	//以下为表单字段
	private String userName;
	
	private String userPwd;
	
	private String confirmPwd;
	
	private String email;
	
	private String birthday;
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public String getConfirmPwd() {
		return confirmPwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	//存放校验失败时的错误信息
	private Map<String, String> errors = new HashMap<String, String>();
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	
	/**
	 * @return
	 */
	public boolean validate() {
		
		boolean isOk = true;
		
		if (this.userName == null || this.userName.trim().equals("")) {
			
			isOk = false;
			errors.put("userName", "用户名不能为空！");
		}else{
			if (!this.userName.matches("[a-zA-Z]{3,8}")) {
				isOk = false;
				errors.put("userName", "用户名必须是3-8位的字母!");
			}
		}
		
		if (this.userPwd == null || this.userPwd.trim().equals("")) {
			
			isOk = false;
			errors.put("userPwd", "密码不能为空！");
		}else {
			if(!this.userPwd.matches("\\d{3,8}")){
				isOk = false;
				errors.put("userPwd", "密码必须是3-8位的数字!");
			}
				
		}
		
		if (this.confirmPwd != null) {
			if (!this.confirmPwd.equals(this.userPwd)) {
				isOk = false;
				errors.put("confirmPwd", "两次输入密码不一致!");
			}
		}
		
		if (this.email != null && !this.email.trim().equals("")) {
			
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOk = false;
				errors.put("email", "邮箱不是一个合法邮箱！");
			}
		}
		
		if (this.birthday != null && !this.email.trim().equals("")) {
			
			try {
				DateLocaleConverter conver = new DateLocaleConverter();
				conver.convert(this.birthday);
			} catch (Exception e) {
				isOk = false;
				errors.put("birthday", "生日必须是一个日期！");
			}
		}
		return isOk;
	}
		
}
