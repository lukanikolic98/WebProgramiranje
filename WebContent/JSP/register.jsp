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
				<input type="text" placeholder="First Name" name ="firstName" required>
			</div>
			<br/>
			<div class="input_box">
				<input type="text" placeholder="Last Name" name ="lastName" required>			
			</div>
			<br/>
			<div class="input_box">
				<input type="username" placeholder="Username" name ="username" required>			
			</div>
			<br/>
			<div class="input_box">
				<input type="email" placeholder="Email" name ="email" required>			
			</div>
			<br/>
			<div class="input_box">
				<input type="password" placeholder="Password" name ="password" required>			
			</div>
			<br/>
			<div class="input_box">
				<input type="password" placeholder="Confirm Password" name ="confirmPassword" required>			
			</div>
			<br/>
			<div class="input_box">
				<div class="radioWrapper">
					<label for="male">Male</label>				
					<input type="radio" id="male" name="gender" value="male"> 
				</div>
				<div class="radioWrapper">
					<label for="female">Female</label>
					<input type="radio" id="female" name="gender" value="female">
				</div>
			</div>
			<% if (request.getAttribute("err") != null) { %>
				<p style="color: red"><%=request.getAttribute("err")%></p>
			<% } %>
			<br/>
			<button type="submit" value="Register">Register</button>
			<div id="registerText">
				<p>Already have an account?<a href="LoginServlet">Log in</a></p>
			</div>
		</form>
	</div>
	
</body>
</html>