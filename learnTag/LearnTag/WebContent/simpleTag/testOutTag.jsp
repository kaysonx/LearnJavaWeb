<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/lsTagLib.tld" prefix="ls"%>
<!DOCTYPE html>
<html>
<head>
<title>Test Out tag</title>
</head>
<body>
	<ls:out content="<a href='http://www.baidu.com'>我是百度</a>"/>
	<hr/>
	<ls:out content="<a href='http://www.baidu.com'>我是百度</a>" escapeHtml="true"/>
</body>
</html>