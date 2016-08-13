package me.learnjava.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * @author liusha
 * ��װ��
 * ���ڼ���ע�������
 */

public class RegistFormBean {
	
	//����Ϊ���ֶ�
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
	
	
	//���У��ʧ��ʱ�Ĵ�����Ϣ
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
			errors.put("userName", "�û�������Ϊ�գ�");
		}else{
			if (!this.userName.matches("[a-zA-Z]{3,8}")) {
				isOk = false;
				errors.put("userName", "�û���������3-8λ����ĸ!");
			}
		}
		
		if (this.userPwd == null || this.userPwd.trim().equals("")) {
			
			isOk = false;
			errors.put("userPwd", "���벻��Ϊ�գ�");
		}else {
			if(!this.userPwd.matches("\\d{3,8}")){
				isOk = false;
				errors.put("userPwd", "���������3-8λ������!");
			}
				
		}
		
		if (this.confirmPwd != null) {
			if (!this.confirmPwd.equals(this.userPwd)) {
				isOk = false;
				errors.put("confirmPwd", "�����������벻һ��!");
			}
		}
		
		if (this.email != null && !this.email.trim().equals("")) {
			
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOk = false;
				errors.put("email", "���䲻��һ���Ϸ����䣡");
			}
		}
		
		if (this.birthday != null && !this.email.trim().equals("")) {
			
			try {
				DateLocaleConverter conver = new DateLocaleConverter();
				conver.convert(this.birthday);
			} catch (Exception e) {
				isOk = false;
				errors.put("birthday", "���ձ�����һ�����ڣ�");
			}
		}
		return isOk;
	}
		
}
