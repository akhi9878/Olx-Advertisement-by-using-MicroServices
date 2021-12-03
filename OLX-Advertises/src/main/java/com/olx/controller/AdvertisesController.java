package com.olx.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.service.AdvertisesService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/olx/advertise")
public class AdvertisesController {
	
	@Autowired
	AdvertisesService advertisesService;
	
	
	@GetMapping(value="/user/advertise",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give all Advertises from Database")
	public @ResponseBody ResponseEntity<List<Advertise>> getAllAdvertises(@RequestHeader("Authorization") String token ){
		return new ResponseEntity<List<Advertise>>(this.advertisesService.getAllAdvertises(token),HttpStatus.OK);
	}
	
	@GetMapping(value="/{postId}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give Advertise based on Advertise Id from Database")
	public ResponseEntity<Advertise> getAdvertiseById(@RequestHeader("Authorization") String token ,@PathVariable("postId") int advertiseId){
		Advertise advertise = this.advertisesService.getAdvertiseById(token,advertiseId);
		return new ResponseEntity<Advertise>(advertise,HttpStatus.OK);
	}
	
	@GetMapping(value="/user/advertise/{postId}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give Advertise based on User and Advertise Id from Database")
	public ResponseEntity<Advertise> getAdvertiseByUserById(@RequestHeader("Authorization") String token ,@PathVariable("postId") int advertiseId){
		Advertise advertise = this.advertisesService.getAdvertiseByUserById(token,advertiseId);
		return new ResponseEntity<Advertise>(advertise,HttpStatus.OK);
	}
	
	@GetMapping(value="/search",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give Advertises based on searchtext from Database")
	public ResponseEntity<List<Advertise>> getAdvertiseBySearch(@RequestParam("searchText") String searchText){
		List<Advertise> advertises = this.advertisesService.getAdvertiseBySearch(searchText);
		return new ResponseEntity<List<Advertise>>(advertises,HttpStatus.OK);
	}
	
	//searchText,category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records
	@GetMapping(value="/search/filtercriteria",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give Advertise based on filterCriteria from Database")
	public ResponseEntity<List<Advertise>> getAdvertiseByFilter(@RequestParam("searchText") String searchText,
			@RequestParam("category") int category,@RequestParam("postedBy") String postedBy,
			@RequestParam("dateCondition") String dateCondition,
			@RequestParam("onDate") LocalDate onDate,
			@RequestParam("fromDate") LocalDate fromDate,@RequestParam("toDate") LocalDate toDate,
			@RequestParam("sortBy") String sortBy,@RequestParam("startIndex") int startIndex,
			@RequestParam("records") int records){
	    //Functionality based on Filters
		List<Advertise> advertises = this.advertisesService.getAdvertiseByFilter(searchText,category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records); 
		return new ResponseEntity<List<Advertise>>(advertises,HttpStatus.OK);
		
	}
	
	@PostMapping(value="/",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will post(creates new) an Advertise to Database")
	public ResponseEntity<Advertise> createNewAdvertise(@RequestBody Advertise advertise,@RequestHeader("Authorization") String token) {		
		Advertise advertiseDto = this.advertisesService.createNewAdvertise(advertise,token);
		return new ResponseEntity<Advertise>(advertiseDto,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST End Point API will give update the Advertise based on Advertise Id to Database")
	public ResponseEntity<Advertise> updateAdvertise(@PathVariable("id") int id,@RequestBody Advertise advertise,@RequestHeader("Authorization") String token) {
		Advertise advertiseDto = this.advertisesService.updateAdvertise(id,advertise, token);	
		return new ResponseEntity<Advertise>(advertiseDto,HttpStatus.OK);
	}
		
	@DeleteMapping(value="/user/advertise/{postId}")
	@ApiOperation(value = "This REST End Point API will delete Advertise based on Advertise Id from Database")
	public ResponseEntity<Boolean> deleteAdvertiseById(@PathVariable("postId") int advertiseId,@RequestHeader("Authorization") String token ) {
		Boolean flag = this.advertisesService.deleteAdvertiseById(advertiseId,token);
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
    
	
	
	

}
