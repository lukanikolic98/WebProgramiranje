<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
 <div id="header">
 	<a id="headerLeft" href="HomeServlet">Home</a>
 	<div class="searchContainer">
	<form action="SearchServlet" method="GET">
		<input type="text" name="searchTerm" placeholder="Search..." required>
	</form>
 	</div>
 	<div class="headerButtonsRight">
 		<a href="EditProfileServlet">Account</a>
	 	<a href="LogoutServlet">Logout</a>
 	</div>
 </div>
</body>
</html>