<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index File</title>
</head>
<body>
   <%
       RequestDispatcher dispatcher = request.getRequestDispatcher("./UserController");
   	   dispatcher.forward(request, response);
   
   %>
</body>
</html>