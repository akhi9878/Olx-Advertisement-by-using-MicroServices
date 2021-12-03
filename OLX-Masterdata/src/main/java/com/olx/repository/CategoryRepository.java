package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer>{
	
	@Query(value = "SELECT c.name from categories c WHERE c.id = :categoryId",nativeQuery=true)
	String findByCategoryId(int categoryId);

}
