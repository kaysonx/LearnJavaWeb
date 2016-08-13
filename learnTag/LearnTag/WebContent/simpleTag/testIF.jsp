<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/lsTagLib.tld" prefix="ls"%>
<!DOCTYPE html>
<html>
<head>
<title>Test IF tag</title>
</head>
<body>
	<ls:if test="true">
		<h2>感觉身体被掏空。。。</h2>
	</ls:if>
	
	<ls:if test="false">
		<h2>喝肾宝。。提神抗疲劳。。。</h2>
	</ls:if>
</body>
</html>