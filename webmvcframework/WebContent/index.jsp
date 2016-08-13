<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
<script type="text/javascript">
	function doLogout() {
		//注销当前登录的用户
		window.location.href = "${pageContext.request.contextPath}/LogoutServlet";
	}
</script>
</head>

<body>
	<h1>学java的Hello World</h1>
	<hr />
	<c:if test="${user==null}">
		<a href="${pageContext.request.contextPath}/reg.do" target="_blank">注册</a>
		<a href="${pageContext.request.contextPath}/log.do">登陆</a>
	</c:if>
	<c:if test="${user!=null}">
   		欢迎您：${user.userName}
   		<input type="button" value="退出登陆" onclick="doLogout()">
	</c:if>
	<hr />
</body>
</html>
