<%@page import="com.pst.jobportal.dto.UserDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pst.jobportal.dto.JobDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Company Home</title>

<body>

    <%
        UserDto user = (UserDto)session.getAttribute("user");
    %>

	<div class="container"> 

	<div>
			<%@include file="title.html"%>
		</div>
		<div class="home-navigation">
			<%@include file="company_home_navigation.jsp"%>
		</div>

		<div class="company-home-body">
		    
		     <h1 style="text-align: center"> Welcome to <%=user.getCompanyName() %></h1>
		
		</div>
	</div>

</body>

</head>

</html>