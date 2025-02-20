<%@page import="com.pst.jobportal.dto.JobDto"%>
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
  padding: 5% 10% 10% 10%;
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

.btn-primary {
  width: 50%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

.btn-primary:hover {
  background-color: #45a049;
}

.btn-warning {
  width: 50%;
  background-color: #dc3545;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn-warning:hover {
  background-color: #45a049;
}

.btn-group {
 display: flex;
}

.btn-group input{
 padding: 10px;
}
  


</style>
<meta charset="ISO-8859-1">
<title>Update Job</title>
<link rel="stylesheet" href="css/home-styles.css" />
</head>
<body>

  <%
      UserDto user = (UserDto)session.getAttribute("user");
  
      JobDto dto = (JobDto) request.getAttribute("jobDto");
  %>

 <div class="container">
 
 		<div>
			<%@include file="title.html"%>
		</div>
		<div class="home-navigation">
			<%@include file="company_home_navigation.jsp"%>
		</div>

		<div class="company-home-body">
		   <div class="page-title"> View Job </div>
		   <div class="job-form">
		   	
		   	 <form>
		   	 
		   	 	<label for="cname">Company Name</label>
			    <input type="text" id="cname" disabled="disabled" value="<%=user.getCompanyName()%>"/>
		   	 
		   	 	 <label for="jId">Job Id</label>
			     <input disabled="disabled" type="text" id="jId" value="<%=dto.getJobId()%>">
		   	 
			    <label for="jname">Job Name</label>
			    <input disabled="disabled" type="text" id="jname" value="<%=dto.getJobName()%>">
			
			    <label for="jType">Job Type</label>
			    <input disabled="disabled" type="text" id="jType" value="<%=dto.getJobType()%>">
			    
			    <label for="date">Date</label>
			    <input disabled="disabled" type="date" id="date" value="<%=dto.getDateOfPosting()%>">
			    
			    <label for="exdate">Expire Date</label>
			    <input disabled="disabled" type="date" id="exdate" value="<%=dto.getExpireDate()%>">
			
			    <label for="jobDescription">Job Description</label>
			    <textarea disabled="disabled" rows="10" cols="10" id="jobDescription"><%=dto.getJobDescription() %></textarea>
  			</form>
		   
		   </div>
		
		</div>
 
 </div>

</body>
</html>