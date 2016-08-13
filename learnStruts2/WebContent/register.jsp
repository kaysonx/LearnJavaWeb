<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>RegistPage</title>
</head>
<body>
	<h3>Type your information to register</h3>
	<s:form action="register" method="post">
		<s:textfield label="username" name="user.name" tooltip="name must be over 3 characters"></s:textfield>
		<s:textfield label="password" type="password" name="user.password" tooltip="password must over 6 digits"></s:textfield>
		<s:textfield label="confirm password" type="password" name="user.confirmPassword"></s:textfield>
		<s:textfield label="age" name="user.age" tooltip="age must over 16"></s:textfield>
		<s:textfield label="mobile" name="user.mobile" tooltip="input correct mobile number format"></s:textfield>
		<s:textfield label="email" name="user/email" tooltip="input correct eamil format"></s:textfield>
		
		<s:submit label="Register"></s:submit>
	</s:form>
</body>
</html>