package com.olx.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.UserInfo;
import com.olx.entity.UserEntity;
import com.olx.repository.UserRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService{
   
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public UserInfo createNewUser(UserInfo userInfo) {
		UserEntity userEntity = this.modelMapper.map(userInfo, UserEntity.class);
		userEntity.setActive(true);
		userEntity.setRoles("ROLE.USER");
		userEntity = this.userRepository.save(userEntity);
		UserInfo userInfoDto = this.modelMapper.map(userEntity,UserInfo.class);
		return userInfoDto;		
	}


	@Override
	public UserInfo findUsername(String databaseUsername) {
		
		UserEntity userEntity = this.userRepository.findUsername(databaseUsername);
		UserInfo userInfoDto = this.modelMapper.map(userEntity,UserInfo.class);
		return userInfoDto;	
		
	}

}
