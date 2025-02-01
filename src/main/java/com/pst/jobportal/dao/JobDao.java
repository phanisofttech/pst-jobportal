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
	private static final String ADD_NEW_JOB = "insert into job(job_namee, job_type, date_of_posting, expire_date, job_description, company_id) values (?, ?, ?, ?, ?, ?)";

	public List<JobDto> getJobsByCompanyId(int companyId) {
		
		List<JobDto> jobList = new ArrayList<JobDto>();
		
		try {
			Connection con = DbConnection.getDataBaseConnection();
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
			Connection con = DbConnection.getDataBaseConnection();
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
	
}
