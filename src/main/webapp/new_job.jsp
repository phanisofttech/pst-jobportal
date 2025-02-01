<%@page import="com.pst.jobportal.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>

.job-form {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 5% 10% 10% 10%;;
}

input[type=text],input[type=date], textarea {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}
  


</style>
<meta charset="ISO-8859-1">
<title>New Job</title>
<link rel="stylesheet" href="css/home-styles.css" />
</head>
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
		   <div class="page-title"> Add New Job </div>
		   <div class="job-form">
		   	
		   	 <form action="./JobController" method="post">
		   	 
		   	 	<label for="cname">Company Name</label>
			    <input type="text" id="cname" disabled="disabled" value="<%=user.getCompanyName()%>"/>
			    <input type="hidden" name="companyId" value="<%=user.getCompanyId()%>"/>
		   	 
			    <label for="jname">Job Name</label>
			    <input type="text" id="jname" name="jobName" placeholder="Job name..">
			
			    <label for="jType">Job Type</label>
			    <input type="text" id="jType" name="jobType" placeholder="Job Type..">
			    
			    <label for="date">Date</label>
			    <input type="date" id="date" name="dateOfPosting" placeholder="Date Of Posting">
			    
			    <label for="exdate">Expire Date</label>
			    <input type="date" id="exdate" name="expireDate" placeholder="Expire Date">
			
			    <label for="jobDescription">Job Description</label>
			    <textarea rows="10" cols="10" id="jobDescription" name="jobDescription"></textarea>
			  
			    <input type="submit" value="Post Job">
  			</form>
		   
		   </div>
		
		</div>
 
 </div>

</body>
</html>