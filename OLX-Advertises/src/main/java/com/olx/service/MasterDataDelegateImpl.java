package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MasterDataDelegateImpl implements MasterDataDelegate {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CircuitBreaker(name = "STATUSNAME-FROM-MASTER-DATA-SERVICE",fallbackMethod = "fallBackMasterData")
	public String findStatusById(int statusId) {
		
		ResponseEntity<String> responseStatusText = this.restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/status/"+statusId, String.class);
		return responseStatusText.getBody();
	
	   //String statusName = this.restTemplate.getForObject("http://localhost:9001/olx/advertise/status/"+statusId, String.class);
		//return statusName;		
	}
	
	public String fallBackMasterData(int statusId,Exception ex) {
		System.out.println("fall Back Method is Called " + ex);
		return null;
	}
	
	@Override
	@CircuitBreaker(name = "CATEGORYNAME-FROM-MASTER-DATA-SERVICE",fallbackMethod = "fallBackMasterDataCategory")
	public String findCategoryById(int categoryId) {
		
		ResponseEntity<String> responseStatusText = this.restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/category/"+categoryId, String.class);
		return responseStatusText.getBody();
	
	    //String categoryName = this.restTemplate.getForObject("http://localhost:9001/olx/advertise/category/"+categoryId, String.class);
		//return categoryName;
		
	}
	
	public String fallBackMasterDataCategory(int categoryId,Exception ex) {
		System.out.println("fall Back Method is Called " + ex);
		return null;
	}
	
	

}
