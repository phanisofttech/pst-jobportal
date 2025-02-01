<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/home-styles.css" />
</head>
<body>
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
				<div class="job-list"></div>
				<div class="job-list"></div>
				<div class="job-list"></div>
				<div class="job-list"></div>
			</div>
		</div>
	</div>
</body>
</html>