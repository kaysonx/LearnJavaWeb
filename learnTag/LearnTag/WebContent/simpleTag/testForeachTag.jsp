<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/lsTagLib.tld" prefix="ls"%>
<!DOCTYPE html>
<html>
<head>
<title>Test Foreach tag</title>
</head>
<%
	List<String> listdata = new ArrayList<String>();
	listdata.add("utlight");
	listdata.add("liusha");
	listdata.add("right");
	
	Integer intObject[] = new Integer[]{1,2,2};
	
	int intArr[] = new int[]{4,5,6};
	
	Map<String,String> mapData = new HashMap<String, String>();
	mapData.put("a", "aaaaa");
	mapData.put("b", "bbbbb");
	
	
	pageContext.setAttribute("listdata", listdata);
	pageContext.setAttribute("intObject", intObject);
	pageContext.setAttribute("intArr", intArr);
	pageContext.setAttribute("mapData", mapData);
%>
<body>
	<ls:foreach items="${listdata }" var="str">
		${str}<br>
	</ls:foreach>
	<hr/>
	<ls:foreach items="${intObject }" var="num">
		${num}<br>
	</ls:foreach>
	<hr/>
	<ls:foreach items="${intArr }" var="num">
		${num}<br>
	</ls:foreach>
	<hr/>
	<ls:foreach items="${mapData }" var="me">
		${me}<br>
	</ls:foreach>
	
</body>
</html>