<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="users" class="dao.UserDAO" scope="application"/>

<title>Edit Profile</title>
</head>
<body>
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
			<div class="edit-profile-form">
			<form action="EditProfileServlet" method="POST">
				<div class="edit-profile-div">
				<label for="firstName">First Name:</label>
				<input type="text" value="<%=user.getFirstName()%>" name="firstName" placeholder="First Name">
				</div>
				<div class="edit-profile-div">
				<label for="lastName">Last Name:</label>
				<input type="text" value="<%=user.getLastName()%>" name="lastName" placeholder="Last Name">
				</div>
				<div class="edit-profile-div">
				<label for="email">Email:</label>
				<input type="text" value="<%=user.getEmail()%>" name="email" placeholder="Email">
				</div>
				<div class="edit-profile-div">
				<label for="oldPassword">Old Password:</label>
				<input type="password" name="oldPassword" placeholder="password">
				</div>
				<div class="edit-profile-div">
				<label for="newPassword">New Password:</label>
				<input type="password" name="newPassword" placeholder="password">
				</div>
				<div class="edit-profile-div">
				<label for="confirmNewPassword">Confirm New Password:</label>
				<input type="password" name="confirmNewPassword" placeholder="password">
				</div>
				<div class="edit-profile-div">
					<label>Gender:</label>
					<div class="edit-profile-div-radio">
						<label for="male">Male:</label>
						<input type="radio" id="radioMale" name="gender" value="male" <%=user.getGender().equals("male")? "checked" : "" %> />
						<label for="female">Female:</label>
						<input type="radio" name="gender" value="female" <%=user.getGender().equals("female")? "checked" : "" %> />
						
					</div>
				</div>
				<div class="edit-profile-div">
				<label for="publicStatus">Public Status:</label>
				<input type="checkbox" value="true" name="publicStatus" <%=user.getPublicStatus()? "checked" : "" %>>
				</div>
				<div class="edit-profile-div-submit">
				<% if (request.getAttribute("err") != null) { %>
					<p style="color: red"><%=request.getAttribute("err")%></p>
				<% } else if(request.getAttribute("success") != null){ %>
					<p style="color: green"><%= request.getAttribute("success") %></p>
				<% } else { %>
					<span></span>
				<% 	} %>
				<button type="submit" value="SaveChanges">Save Changes</button>
				</div>
			</form>
			</div>
		</div>
		</div>
	</main>
	</div>
	
</body>
</body>
</html>