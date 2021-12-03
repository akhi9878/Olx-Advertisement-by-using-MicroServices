package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/olx/masterdata")
public class MasterDataController {
	
	@Autowired
	private MasterDataService masterDataService;
	
	@GetMapping(value="/advertise/category",produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="This REST End Point gives all Categories from Database")
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<List<Category>>(this.masterDataService.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping(value="/advertise/status",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="This REST End Point gives all Statuses from Database")
	public ResponseEntity<List<Status>> getAllStatuses(){
		return new ResponseEntity<List<Status>>(this.masterDataService.getAllStatuses(),HttpStatus.OK);
	}
	
	@GetMapping(value="/advertise/status/{id}")
	@ApiOperation(value="This REST End Point gives Status Name based on Status Id from Database")
	public String findStatusById(@PathVariable("id") int statusId) {
		return this.masterDataService.findStatusById(statusId);
	}
	
	@GetMapping(value="/advertise/category/{id}")
	@ApiOperation(value="This REST End Point gives Category Name based on Category Id from Database")
	public String findCategoryById(@PathVariable("id") int categoryId) {
		return this.masterDataService.findCategoryById(categoryId);
	}
	

}
