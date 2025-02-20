package com.pst.jobportal.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.pst.jobportal.bo.JobBo;
import com.pst.jobportal.dao.JobDao;
import com.pst.jobportal.dto.JobDto;
import com.pst.jobportal.vo.JobVo;

public class JobService {

	private JobDao dao = new JobDao();
	
	public List<JobDto> getJobsByCompanyId(int companyId) {
		return dao.getJobsByCompanyId(companyId);
	}
	
	public int createNewJob(JobVo vo) {
		
		JobBo bo = new JobBo();
		bo.setCompanyId(vo.getCompanyId());
		bo.setJobName(vo.getJobName());
		bo.setJobType(vo.getJobType());
		bo.setDescription(vo.getDescription());
		
		String exDate = vo.getExpireDate();
		Date expireDate = Date.valueOf(exDate);
		bo.setExpireDate(expireDate);
		
		String dop = vo.getDateOfPosting();
		Date dateOfPosting = Date.valueOf(dop);
		bo.setDateOfPosting(dateOfPosting);
		
		// calling job dao method
		return dao.addJob(bo);
	}
	
	public int updateJob(JobVo vo, int jobId) {
		
		JobBo bo = new JobBo();
		bo.setJobName(vo.getJobName());
		bo.setJobType(vo.getJobType());
		bo.setDescription(vo.getDescription());
		
		String exDate = vo.getExpireDate();
		Date expireDate = Date.valueOf(exDate);
		bo.setExpireDate(expireDate);
		
		String dop = vo.getDateOfPosting();
		Date dateOfPosting = Date.valueOf(dop);
		bo.setDateOfPosting(dateOfPosting);
		
		int i = dao.updateJob(bo, jobId);
		return i;
	}
	
	public JobDto getJob(int jobId) {
		return dao.getJobById(jobId);
	}
	
	public int deleteJob(int jobId) {
		return dao.deleteJobById(jobId);
	}
	
	public List<JobDto> getActiveJobs(int accountId) {
		List<JobDto> list = null;
		if(accountId != 0) {
			list = dao.getAllActiveAndNotAppliedJobs(accountId);
		} else {
			list = dao.getAllActiveJobs();
		}
		return list;
	}
	
	public int applyJob(int accountId, int jobId) {
		return dao.applyJob(accountId, jobId);
	}
}
