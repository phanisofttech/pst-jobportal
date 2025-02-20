<%@page import="com.pst.jobportal.dto.JobDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.data-table {
	font-family: monospace;
	margin-top: 3%;
	font-size: 20px;
}

.data-table table {
	width: 100%;
}

.data-table table tr th {
	background-color: darkgray;
	color: white;
	padding: 10px;
}

.data-table table tr td {
	text-align: center;
}

.data-table table tr td a i {
	padding: 2%;
}

.new-job a {
	width: 11%;
	background-color: coral;
	padding: 10px;
	text-decoration: none;
	color: white;
	font-family: monospace;
	font-size: 15px;
	font-weight: bolder;
}

.new-job {
	text-align: right;
	margin-top: 6%;
	margin-right: 6%;
}
</style>
<meta charset="ISO-8859-1">
<title>Jobs</title>
<link rel="stylesheet" href="css/home-styles.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%
	List<JobDto> list = request.getAttribute("jobList") == null ? null : (List<JobDto>) request.getAttribute("jobList");
	String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
	String color = request.getParameter("color") == null ? "" : request.getParameter("color");
	%>

	<div class="container">

		<div>
			<%@include file="title.html"%>
		</div>
		<div class="home-navigation">
			<%@include file="company_home_navigation.jsp"%>
		</div>

		<div class="company-home-body">
			<div class="new-job">
				<a href="./new_job.jsp">Create New Job</a>
			</div>
			<div style="text-align:center; color: <%=color%>;">
				<h4><%=msg%></h4>
			</div>
			<div class="data-table">

				<table>
					<tr>
						<th>JOB ID</th>
						<th>JOB NAME</th>
						<th>JOB TYPE</th>
						<th>COMPANY</th>
						<th>ACTIONS</th>
					</tr>
					<%
					if (list != null) {
						for (JobDto dto : list) {
					%>
					<tr>
						<td><%=dto.getJobId()%></td>
						<td><%=dto.getJobName()%></td>
						<td><%=dto.getJobType()%></td>
						<td><%=dto.getCompanyName()%></td>
						<td><a href="./JobController?action=update&jobId=<%=dto.getJobId()%>"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>
							<a href="./JobController?action=view&jobId=<%=dto.getJobId()%>"><i class="fa fa-file" aria-hidden="true"></i></a> 
							<a href="./JobController?action=delete&jobId=<%=dto.getJobId()%>" onclick="displayConfirmBox(event)"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
					</tr>
					<%
					}
					}
					%>

				</table>

			</div>


		</div>




	</div>
<script type="text/javascript">

	function displayConfirmBox(event) {
		debugger;
		const displayText = "Do you want to delete this job ? \n Press Ok to confirm";
		let val = confirm(displayText);
		if(val === false) {
			event.preventDefault();
		}
	}

</script>
</body>
</html>