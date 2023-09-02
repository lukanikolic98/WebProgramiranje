<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/loginRegister.css" rel="stylesheet" type="text/css" >
<title>The Social Network</title>
</head>
<body>
	<div id="loginForm">
		<form action="RegisterServlet" method="POST">
			<h1> Welcome to Web Book Social Network!</h1>
			<div class="input_box">
			<input type="text" placeholder="First Name" name ="firstname" required>
			</div>
			<br/>
			<div class="input_box">
			<input type="text" placeholder="Last Name" name ="lastname" required>			
			</div>
			<br/>
			<div class="input_box">
			<input type="email" placeholder="Email" name ="email" required>			
			</div>
			<br/>
			<div class="input_box">
			<input type="username" placeholder="Username" name ="username" required>			
			</div>
			<br/>
			<div class="input_box">
			<input type="password" placeholder="Password" name ="password" required>			
			</div>
			<% if (request.getAttribute("err") != null) { %>
				<p style="color: red"><%=request.getAttribute("err")%>></p>
			<% } %>
			<br/>
			<button type="submit" value="Register">Register</button>
			<div id="registerText">
				<p>Already have an account?<a href="#">Log in</a></p>
			</div>
		</form>
	</div>
	
</body>
</html>