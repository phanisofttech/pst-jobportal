package com.pst.jobportal.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pst.jobportal.dto.UserDto;
import com.pst.jobportal.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginService service = new LoginService();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDto dto = service.userLogin(email, password);
		session.setAttribute("user", dto);
		
		if(dto != null && !dto.getName().equals("") && dto.getRoleName().equals("admin")) {
			dispatcher = request.getRequestDispatcher("admin_home.jsp");
			dispatcher.forward(request, response);
		}else if(dto != null && !dto.getName().equals("") && dto.getRoleName().equals("user")) {
			dispatcher = request.getRequestDispatcher("user_home.jsp");
			dispatcher.forward(request, response);
		} else if(dto != null && !dto.getName().equals("") && dto.getRoleName().equals("company")) {
			//dispatcher = request.getRequestDispatcher("./JobController");
			//dispatcher.forward(request, response);
			response.sendRedirect("./company_home.jsp");
		} else {
		    response.sendRedirect("./login.jsp?msg=Login Failed, Please Try Again");
		}
	
		
	}

}
