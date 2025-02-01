package com.pst.jobportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pst.jobportal.dto.UserDto;
import com.pst.jobportal.util.DbConnection;

public class LoginDao {

	
	private static final String GET_USER_ACCOUNT = "select * from account where email=? and password=?";
	//private static final String GET_USER_ACCOUNT_WITH_ROLE = "select a.account_id, a.name, a.mobile_number, a.email, a.password, r.role_name, c.company_id,  from account a inner join role r on a.role_id=r.role_id where email=? and password=?";
	private static final String GET_USER_ACCOUNT_WITH_ROLE = "select a.account_id, a.name, a.mobile_number, a.email, a.password, r.role_name, c.company_id, c.company_name from account a inner join role r on a.role_id = r.role_id left join company c on a.company_id = c.company_id where email=? and password=?";
	
	
	public UserDto doLogin(String email, String password) {
		UserDto dto = null;
		try {
			Connection con = DbConnection.getDataBaseConnection();
			PreparedStatement ps = con.prepareStatement(GET_USER_ACCOUNT_WITH_ROLE);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				long mobileNumber = rs.getLong(3);
				String userEmail = rs.getString(4);
				String userPassword = rs.getString(5);
				String roleName = rs.getString(6);
				int companyId = rs.getInt(7);
				String companyName = rs.getString(8);
				
				
				dto = new UserDto();
				dto.setUserId(id);
				dto.setName(name);
				dto.setMobileNumber(mobileNumber);
				dto.setEmail(userEmail);
				dto.setPassword(userPassword);
				dto.setRoleName(roleName);
				dto.setCompanyId(companyId);
				dto.setCompanyName(companyName);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
}
