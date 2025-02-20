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
	private String message = "";
	private String color = "";
	
	private JobService service = new JobService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String action = request.getParameter("action");
		
		if(action != null && (action.equals("update") || action.equals("view"))) {
			  // update flow, getting data based on job id and display update job jsp file
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			JobDto dto = service.getJob(jobId);
			System.out.println("job name: "+ dto.getJobName());
			request.setAttribute("jobDto", dto);
			
			if(action.equals("update")) {
				dispatcher = request.getRequestDispatcher("update_job.jsp");
			} else if(action.equals("view")) {
				dispatcher = request.getRequestDispatcher("view_job.jsp");
			}
			
			dispatcher.forward(request, response);
			
		} else if(action != null && (action.equals("delete"))) {
			// Delete Flow
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			int i = service.deleteJob(jobId);
			if(i > 0) {
		    	message = "Job Deleted Successfully";
		    	color = "green";
		    }else {
		    	message = "Job Deletion failed";
		    	color = "red";
		    }
			response.sendRedirect("./UserController?message="+message+"&color="+color);
			
		}else if(action != null && action.equals("applyJob")){
			HttpSession session = request.getSession();
			UserDto user = (UserDto)session.getAttribute("user");
			
			
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			
			int userId = user.getUserId();
			int i = service.applyJob(userId, jobId);
			if(i > 0) {
				message = "Job applied successfully";
				color = "green";
			} else {
				message = "Internal Error, Please try again";
				color = "red";
			}
			response.sendRedirect("./UserController?msg="+message+"&color="+color+"&action=userHome&userId="+userId);
			
		}
		else {
			// list of jobs flow
			HttpSession session = request.getSession();
			
			UserDto user = (UserDto)session.getAttribute("user");
			
			String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
			String color = request.getParameter("color") == null ? "" : request.getParameter("color");
			
			int companyId = user.getCompanyId();
			
			
			List<JobDto> list = service.getJobsByCompanyId(companyId);
			request.setAttribute("jobList", list);
			dispatcher = request.getRequestDispatcher("./jobs.jsp?msg="+msg+"&color:"+color);
			dispatcher.forward(request, response);	
		}
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int i = 0;
			String action = req.getParameter("action");
			
		    int companyId = action.equals("create-form") ? Integer.parseInt(req.getParameter("companyId")) : 0;
		    int jobId = action.equals("update-form") ? Integer.parseInt(req.getParameter("jobId")) : 0;
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
		    
		    if(action.equals("create-form")) {
		    	i = service.createNewJob(vo);
		    	if(i > 0) {
			    	message = "Job successfully inserted";
			    	color = "green";
			    }else {
			    	message = "Job insertion failed";
			    	color = "red";
			    }
		    } else {
		    	// call update method in service
		    	i = service.updateJob(vo, jobId);
		    	
		    	if(i > 0) {
			    	message = "Job Updated Successfully";
			    	color = "green";
			    }else {
			    	message = "Job Updation failed";
			    	color = "red";
			    }
		    }
		    
		    
		    resp.sendRedirect("./JobController?msg="+message+"&color="+color);
		}
	
	

}
