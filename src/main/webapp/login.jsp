<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/home-styles.css" />
<link rel="stylesheet" href="css/login-styles.css" />
</head>
<body>
	<%
		String message = request.getParameter("msg") == null ? "" : request.getParameter("msg");
	%>
	<div class="container">
		<div>
			<%@include file="title.html"%>
		</div>
		<div class="home-navigation">
			<%@include file="home_navigation.jsp"%>
		</div>

		<div class="home-body">
			<div class="home-left-body">
				<%@include file="news.jsp"%>
			</div>
			<div class="home-right-body">
				<div class="login-form">
					<div class="validation-message">
					     <h4><%=message %></h4>
					</div>
					<form action="./LoginController" method="post">
						<div class="form-controll-group">
							<label> Enter Email </label> 
							<input type="text" name="email" />
						</div>
						<div class="form-controll-group">
							<label> Enter Password </label> 
							<input type="password" name="password" />
						</div>
						<div class="btn-group">
							<input class="submit-btn" type="submit" value="Login" /> <input
								class="reset-btn" type="reset" name="Clear" />
						</div>
					</form>

				</div>
			</div>
		</div>

	</div>
</body>
</html>