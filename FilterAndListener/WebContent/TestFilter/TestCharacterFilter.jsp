<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>这里是想学java的coder的第四个java程序~~~</title>

</head>

<body>
	<h1>这里是想学java的coder的第四个java程序~~~</h1>
	<hr />
	<c:url value="/ServletDemo1" scope="page" var="servletDemo1">
		<c:param name="username" value="中文走一波"></c:param>
	</c:url>
	
	<a href="${servletDemo1}">超链接方式(get)</a>
	<hr/>
	<form action="${pageContext.request.contextPath}/ServletDemo1" method="post">
		用户名：<input type="text" name="username" value="utlight"/>
		<input type="submit" value="post">
	</form>
</body>
</html>
