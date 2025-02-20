package com.pst.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pst.jobportal.bo.JobBo;
import com.pst.jobportal.dto.JobDto;
import com.pst.jobportal.util.DbConnection;

public class JobDao {
	
	
	private static final String GET_JOBS_BY_COMPANY = "select j.job_id, j.job_name, j.job_type, j.date_of_posting, j.expire_date, j.job_description, c.company_name from job j inner join company c on j.company_id = c.company_id where j.company_id = ? order by job_id asc";
	private static final String ADD_NEW_JOB = "insert into job(job_name, job_type, date_of_posting, expire_date, job_description, company_id) values (?, ?, ?, ?, ?, ?)";
	private static final String GET_JOB_BY_ID = "select * from job where job_id = ?";
	private static final String UPDATE_JOB = "update job set job_name=?, job_type=?, date_of_posting = ?, expire_date = ?, job_description = ? where job_id = ?";
	private static final String DELETE_JOB = "delete from job where job_id = ?";
	private static final String GET_ACTIVE_JOBS = "select job_id, job_name, job_description, date_of_posting from job where expire_date >= curdate()";
	private static final String GET_ACTIVE_AND_NOT_APPLIED_JOBS = "select job_id, job_name, job_description, date_of_posting from job where expire_date >= curdate() and job_id NOT IN(select job_id from account_job_apply where account_id = ?)";
	private static final String APPLY_JOB = "insert into account_job_apply(account_id, job_id, date_of_apply)values(?, ?, curdate())";
	
	private Connection con = DbConnection.getDataBaseConnection();

	public List<JobDto> getJobsByCompanyId(int companyId) {
		
		List<JobDto> jobList = new ArrayList<JobDto>();
		
		
		try {
			PreparedStatement ps = con.prepareStatement(GET_JOBS_BY_COMPANY);
			ps.setInt(1, companyId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JobDto dto = new JobDto();
				dto.setJobId(rs.getInt(1));
				dto.setJobName(rs.getString(2));
				dto.setJobType(rs.getString(3));
				dto.setDateOfPosting(rs.getDate(4));
				dto.setExpireDate(rs.getDate(5));
				dto.setJobDescription(rs.getString(6));
				dto.setCompanyName(rs.getString(7));
				jobList.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jobList;
	}
	
	/*
	 * Input => Vo, BO, individual arguments (As method args)
	 * Output => DTO, individual single argument (As method return type)
	 */
	
	public int addJob(JobBo bo) {
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(ADD_NEW_JOB);
			ps.setString(1, bo.getJobName());
			ps.setString(2, bo.getJobType());
			ps.setDate(3, bo.getDateOfPosting());
			ps.setDate(4, bo.getExpireDate());
			ps.setString(5, bo.getDescription());
			ps.setInt(6, bo.getCompanyId());
			i = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public JobDto getJobById(int jobId) {
		JobDto dto = new JobDto();
		try {
			PreparedStatement ps = con.prepareStatement(GET_JOB_BY_ID);
			ps.setInt(1, jobId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dto.setJobId(rs.getInt(1));
				dto.setJobName(rs.getString(2));
				dto.setJobType(rs.getString(3));
				dto.setDateOfPosting(rs.getDate(4));
				dto.setExpireDate(rs.getDate(5));
				dto.setJobDescription(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int updateJob(JobBo bo, int jobId) {
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_JOB);
			ps.setString(1, bo.getJobName());
			ps.setString(2, bo.getJobType());
			ps.setDate(3, bo.getDateOfPosting());
			ps.setDate(4, bo.getExpireDate());
			ps.setString(5, bo.getDescription());
			ps.setInt(6, jobId);
			i = ps.executeUpdate();
		} catch(Exception e) {e.printStackTrace();}
		return i;
	}
	
	public int deleteJobById(int jobId) {
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_JOB);
			ps.setInt(1, jobId);
			i = ps.executeUpdate();
		} catch(Exception e) {e.printStackTrace();}
		return i;
	}
	
	public List<JobDto> getAllActiveJobs() {
		List<JobDto> jobList = new ArrayList<JobDto>();
		try {
			PreparedStatement ps = con.prepareStatement(GET_ACTIVE_JOBS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JobDto dto = new JobDto();
				dto.setJobId(rs.getInt(1));
				dto.setJobName(rs.getString(2));
				dto.setJobDescription(rs.getString(3));
				dto.setDateOfPosting(rs.getDate(4));
				
				// add dto into list collection
				jobList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return jobList;
	}
	
	public List<JobDto> getAllActiveAndNotAppliedJobs(int accountId) {
		List<JobDto> jobList = new ArrayList<JobDto>();
		try {
			PreparedStatement ps = con.prepareStatement(GET_ACTIVE_AND_NOT_APPLIED_JOBS);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JobDto dto = new JobDto();
				dto.setJobId(rs.getInt(1));
				dto.setJobName(rs.getString(2));
				dto.setJobDescription(rs.getString(3));
				dto.setDateOfPosting(rs.getDate(4));
				
				// add dto into list collection
				jobList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return jobList;
	}
	
	public int applyJob(int account_id, int job_id) {
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(APPLY_JOB);
			ps.setInt(1, account_id);
			ps.setInt(2, job_id);
			i = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
}
