<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>这里是想学java的coder的第四个java程序~~~</title>

</head>

<body>
	<form action="${pageContext.request.contextPath }/ServletDemo2" method="post">
		留言:
		<textarea rows="8" cols="70" name="message">
			<script type="text/javascript">
				while(true){
					alert('我要上天了...');
				}
			</script>
			<a href="http://www.cnblogs.com">访问博客园</a>
		</textarea>
		
		<input type="submit" value="发表" />
	</form>
</body>
</html>