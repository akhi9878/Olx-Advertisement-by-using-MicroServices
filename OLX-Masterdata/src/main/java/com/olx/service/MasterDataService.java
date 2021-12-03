package com.olx.service;

import java.util.List;

import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterDataService {

	public List<Category> getAllCategories();
	public List<Status> getAllStatuses();
	public String findStatusById(int statusId);
	public String findCategoryById(int categoryId);
}
