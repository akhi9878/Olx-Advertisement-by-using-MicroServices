package com.olx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity,Integer> {

	//Optional<AdvertiseEntity> findByUsernameAndById(int advertiseId);
	
   @Query(value = "SELECT * from advertises WHERE title LIKE %:searchText% or category LIKE %:searchText% or description LIKE %:searchText%",nativeQuery=true)
   List<AdvertiseEntity> findBySearch(String searchText);
   
   @Query(value = "SELECT COUNT(*) from advertises",nativeQuery = true)
   long findByCount();
   
   @Query(value = "SELECT COUNT(*) from advertises WHERE active = '1'",nativeQuery = true)
   long findActiveCount();
   
   @Query(value = "SELECT COUNT(*) from advertises WHERE active = '0'",nativeQuery = true)
   long findClosedCount();
	
	
}
