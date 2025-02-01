package com.pst.jobportal.service;

import com.pst.jobportal.dao.LoginDao;
import com.pst.jobportal.dto.UserDto;

public class LoginService {

	private LoginDao dao = new LoginDao();
	
	public UserDto userLogin(String email, String password) {
		UserDto dto = dao.doLogin(email, password);
		return dto;
	}
	
}
