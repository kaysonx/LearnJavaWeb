<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	uploader:<s:property value="+uploader"/><br/>
	filename:<s:property value="+uploadFileName"/><br/>
	filetype:<s:property value="+uploadContentType"/><br/>
	file address:
	<p>
		<%=basepath %><s:property value="'uploadFiles/'+uploadFileName" />

	</p><br/>
</body>
</html>