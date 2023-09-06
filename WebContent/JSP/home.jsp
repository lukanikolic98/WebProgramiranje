<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="beans.User"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/loginRegister.css" rel="stylesheet" type="text/css" >
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="users" class="dao.UserDAO" scope="application"/>
<title>The Social Network</title>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<div class="wrapper">
	<div>
		<ul>
		<form action="SearchServlet" method="GET">
		<li>
			<label for="firstName">First name:</label>		
			<input type="text" name="firstName">
		</li>
		<li>
			<label for="lastName">Last name:</label>
			<input type="text" name="lastName">
		</li>
		<li>
			<label for="start">Start date:</label>
			<input id="start" name="startDate" type="date" value="1930-01-01" min="1930-01-01" max="2010-12-31"">
		</li>
		<li>
			<label for="end">End date:</label>
			<input id="end" name="endDate" type="date" value="2010-12-31" min="1930-01-01" max="2010-12-31"">
		</li>
		<li>
			<button type="submit">Search</button>
		</li>
		</form>
		</ul>
	</div>
	<main>
		<div class="wrapper_inner">
			<% for(User u : users.getResult()) {%>
				<div class="search-item">
					<label><%=u.getFirstName() %></label>
					<label><%=u.getLastName() %></label>
					<label><%=u.getUsername() %></label>
					<a href="UserServlet">Visit profile</a>
				</div>
			<%} %>
		<div class="search-item">
		<h1>Welcome Home!</h1>
		<p>Welcome, <%= user.getFirstName() +", " +user.getRole() %></p>
		</div>
		</div>
	</main>
	</div>
	
</body>
</html>