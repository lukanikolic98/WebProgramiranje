<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/loginRegister.css" rel="stylesheet" type="text/css" >
<jsp:useBean id="user" class="beans.User" scope="session"/>
<title>The Social Network</title>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<div id="loginForm">
		<h1>Welcome Home!</h1>
		<p>Welcome, <%=user.getFirstName() +", " +user.getRole()%></p>
	</div>
	
</body>
</html>