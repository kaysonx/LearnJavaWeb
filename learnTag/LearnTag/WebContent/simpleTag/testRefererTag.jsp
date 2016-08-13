<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 导入自定义标签库 前缀随意 -->
<%@taglib uri="/lsTaglib" prefix="ls"%>

<ls:referer site="http://localhost:8080" page="/index.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>我是你的对象</title>
</head>
<body>
	<p>
		<img alt="我是你的对象" src="${pageContext.request.contextPath}/img/duixiang.jpg"">
	</p>
</body>
</html>