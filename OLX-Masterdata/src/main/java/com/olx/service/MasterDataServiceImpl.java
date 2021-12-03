package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.repository.CategoryRepository;
import com.olx.repository.StatusRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Category> getAllCategories() {
		
		List<CategoryEntity> categoryEntities= categoryRepository.findAll();
		List<Category> categoryDtoA= new ArrayList<Category>();
		 
		//Converts properties of Entity to Dtos
		TypeMap<CategoryEntity,Category> typeMap = modelMapper.typeMap(CategoryEntity.class,Category.class);
		typeMap.addMappings((mapper)->{
			mapper.map(source->source.getCategory(),Category::setCategoryName);
		});
		
		for(CategoryEntity categoryEntity : categoryEntities) {
			Category category = this.modelMapper.map(categoryEntity,Category.class);
			categoryDtoA.add(category);
		}
		return categoryDtoA;
	}

	
	@Override
	public List<Status> getAllStatuses() {
		List<StatusEntity> statusEntities = this.statusRepository.findAll();
		List<Status> statusDtoA = new ArrayList<Status>();
		
		for(StatusEntity statusEntity : statusEntities) {
			Status status = this.modelMapper.map(statusEntity,Status.class);
			statusDtoA.add(status);			
		}
		
		return statusDtoA;
		
	}


	@Override
	public String findStatusById(int statusId) {
		String statusName = this.statusRepository.findByStatusId(statusId);
		return statusName;
	}


	@Override
	public String findCategoryById(int categoryId) {
		String categoryName = this.categoryRepository.findByCategoryId(categoryId);
		return categoryName;
	}

 

}
