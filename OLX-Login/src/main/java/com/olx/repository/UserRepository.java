package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

			List<UserEntity> findByUsername(String username);
			
			@Query(value = "SELECT * FROM users WHERE username = :username",nativeQuery=true)
			UserEntity findUsername(String username);
}
