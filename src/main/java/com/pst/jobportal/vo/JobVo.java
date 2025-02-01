package com.pst.jobportal.vo;

public class JobVo {

	private int companyId;
	private String jobName;
	private String jobType;
	private String dateOfPosting;
	private String expireDate;
	private String description;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getDateOfPosting() {
		return dateOfPosting;
	}
	public void setDateOfPosting(String dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
