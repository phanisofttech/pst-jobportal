<%@page import="com.pst.jobportal.dto.JobDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
<link rel="stylesheet" href="css/home-styles.css" />
<link rel="stylesheet" href="css/login-styles.css" />
</head>
<body>
 	<%
    
        List<JobDto> list = request.getAttribute("active_job_list") == null ? null : (List<JobDto>)request.getAttribute("active_job_list");
        String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
        String color = request.getParameter("color") == null ? "" : request.getParameter("color");
    %>

	<div class="container">
		<div>
			<%@include file="title.html"%>
		</div>
		<div class="home-navigation">
			<%@include file="user_home_navigation.jsp"%>
		</div>
		<div>
		    <center><h4 style="color: <%=color%>"><%=msg %></h4></center>
		</div>
		<div class="home-body">
			<div class="home-left-body">
				<%@include file="news.jsp"%>
			</div>
			<div class="home-right-body">
				<% 
				if(list != null) {
				for(int i = 0; i < list.size(); i++) { 
			    JobDto dto = list.get(i);
			%>
				<div class="job-list">
				   <div class="job-list-head">
				       <div><b>Job Id :</b> <%=dto.getJobId() %></div>
				       <div><b>Date :</b> <%=dto.getDateOfPosting() %></div>
				   </div>
				   <div class="job-list-name">
				       <b> Job Name : </b> <%=dto.getJobName() %>
				   </div>
				   <div class="job-list-description">
				       <b> Job Description :</b> <%=dto.getJobDescription() %>
				   </div>
				   <div class="job-list-button">
				      <a href="./JobController?action=applyJob&jobId=<%=dto.getJobId() %>">Apply Job</a>
				   </div>
				
				</div>
				<% 
					}
					}
				%>
			</div>
		</div>
	</div>

</body>
</html>