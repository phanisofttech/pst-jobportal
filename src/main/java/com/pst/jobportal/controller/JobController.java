package com.pst.jobportal.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.pst.jobportal.dto.JobDto;
import com.pst.jobportal.dto.UserDto;
import com.pst.jobportal.service.JobService;
import com.pst.jobportal.vo.JobVo;

/**
 * Servlet implementation class JobController
 */
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JobService service = new JobService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		UserDto user = (UserDto)session.getAttribute("user");
		
		String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
		String color = request.getParameter("color") == null ? "" : request.getParameter("color");
		
		int companyId = user.getCompanyId();
		
		
		List<JobDto> list = service.getJobsByCompanyId(companyId);
		request.setAttribute("jobList", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jobs.jsp?msg="+msg+"&color:"+color);
		dispatcher.forward(request, response);	
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String message = "";
			String color = "";
		    int companyId = Integer.parseInt(req.getParameter("companyId"));
		    String jobName = req.getParameter("jobName");
		    String jobType = req.getParameter("jobType");
		    String dateOfPosting = req.getParameter("dateOfPosting");
		    String expireDate = req.getParameter("expireDate");
		    String description = req.getParameter("jobDescription");
		    
		    // setting data into jobvo
		    
		    JobVo vo = new JobVo();
		    vo.setCompanyId(companyId);
		    vo.setJobName(jobName);
		    vo.setJobType(jobType);
		    vo.setDateOfPosting(dateOfPosting);
		    vo.setExpireDate(expireDate);
		    vo.setDescription(description);
		    
		    // calling service method
		    int i = service.createNewJob(vo);
		    if(i > 0) {
		    	message = "Job successfully inserted";
		    	color = "green";
		    }else {
		    	message = "Job insertion failed";
		    	color = "red";
		    }
		    
//		    resp.sendRedirect("./jobs.jsp?msg="+message+"&color="+color);
		    resp.sendRedirect("./JobController?msg="+message+"&color="+color);
		
		}

}
