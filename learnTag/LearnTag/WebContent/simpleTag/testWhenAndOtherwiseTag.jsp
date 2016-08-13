<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/lsTagLib.tld" prefix="ls"%>
<!DOCTYPE html>
<html>
<head>
<title>Test When and Otherwise tag</title>
</head>
<body>
	<%
		boolean user = true;
	%>
	<ls:choose>
		<ls:when test="${user}">
			when标签输出内容：
			<h3>用户为空</h3>
		</ls:when>
		<ls:otherwise>
			otherwise标签输出内容：
			<h3>用户不为空</h3>
		</ls:otherwise>
	</ls:choose>
	
	<ls:choose>
		<ls:when test="${!user}">
			when标签输出内容：
			<h3>用户不为空</h3>
		</ls:when>
		<ls:otherwise>
			otherwise标签输出内容：
			<h3>用户为空</h3>
		</ls:otherwise>
	</ls:choose>
</body>
</html>