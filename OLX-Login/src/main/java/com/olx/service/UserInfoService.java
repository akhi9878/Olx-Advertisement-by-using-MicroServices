package com.olx.service;

import com.olx.dto.UserInfo;

public interface UserInfoService {

	public UserInfo createNewUser(UserInfo userInfo);
	public UserInfo findUsername(String databaseUsername);
}
