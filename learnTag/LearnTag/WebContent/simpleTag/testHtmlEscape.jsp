<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/lsTagLib.tld" prefix="ls"%>
<!DOCTYPE html>
<html>
<head>
<title>Test HtmlEscape tag</title>
</head>
<body>
	<ls:htmlEscape>
		<a href="http://www.baidu.com">百度一下 </a>
	</ls:htmlEscape>
</body>
</html>