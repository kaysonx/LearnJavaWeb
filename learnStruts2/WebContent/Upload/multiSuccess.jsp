<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Struts!</title>
</head>
<body>
	<%
		String path = request.getContextPath();
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	
	upload succeed!<br/>
	

	
	
	filename:<s:property value="+uploadsFileName[0]"/><br/>
	filetype:<s:property value="+uploadsContentType[0]"/><br/>
	file address:
	<p>
		<%=basepath %><s:property value="'uploadsMultiFiles/'+uploadsFileName[0]" />
		<a href="download.action?filename=<s:property value="+ uploadsFileName[0]"/>">download</a> <br/>
	</p><br/>
	
		filename:<s:property value="+uploadsFileName[1]"/><br/>
	filetype:<s:property value="+uploadsContentType[1]"/><br/>
	file address:
	<p>
		<%=basepath %><s:property value="'uploadsMultiFiles/'+uploadsFileName[1]" />
		<a href="download.action?filename=<s:property value="+ uploadsFileName[1]"/>">download</a> <br/>
	</p><br/>
	
		filename:<s:property value="+uploadsFileName[2]"/><br/>
	filetype:<s:property value="+uploadsContentType[2]"/><br/>
	file address:
	<p>
		<%=basepath %><s:property value="'uploadsMultiFiles/'+uploadsFileName[2]" />
		<a href="download.action?filename=<s:property value="+ uploadsFileName[2]"/>">download</a> <br/>
	</p><br/>
	
</body>
</html>