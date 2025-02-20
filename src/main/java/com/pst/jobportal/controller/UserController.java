package com.pst.jobportal.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.pst.jobportal.dto.JobDto;
import com.pst.jobportal.service.JobService;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	
	public UserController() {
		System.out.println("i am calling");
	}
	
	@Override
		public void init() throws ServletException {
		System.out.println("i am calling init");
		}
	
	private static final long serialVersionUID = 1L;
	
	private JobService service = new JobService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String msg = request.getParameter("msg") == null ? "" :request.getParameter("msg");
		String color = request.getParameter("color") == null ? "" :request.getParameter("color");
		//String action = request.getAttribute("action") == null ? null : (String)request.getParameter("action");
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		int userId = request.getParameter("userId") == null ? 0 : Integer.parseInt(request.getParameter("userId"));
		List<JobDto> list = service.getActiveJobs(userId);
		request.setAttribute("active_job_list", list);
		if(action != null && action.equals("userHome")) {
			dispatcher = request.getRequestDispatcher("user_home.jsp?msg="+msg+"&color="+color);
		} else {
			dispatcher = request.getRequestDispatcher("home.jsp");
		}
		dispatcher.forward(request, response);
	}

}
