package com.olx.service;

import java.time.LocalDate;
import java.util.List;

import com.olx.dto.Advertise;

public interface AdvertisesService {

	public List<Advertise> getAllAdvertises(String token);
	
	public Advertise getAdvertiseById(String token,int advertiseId);
	
	public Advertise getAdvertiseByUserById(String token,int advertiseId);
	
	public List<Advertise> getAdvertiseBySearch(String searchText);
	
	public List<Advertise> getAdvertiseByFilter(String searchText,int category,String postedBy,
			String dateCondition,LocalDate onDate,LocalDate fromDate,LocalDate toDate,
			String sortBy, int startIndex,int records);
	
	public Advertise createNewAdvertise(Advertise advertise,String token);
	
	public Advertise updateAdvertise(int advertiseId,Advertise advertise,String token);
	
	public boolean deleteAdvertiseById(int advertiseId, String token);
	
	
	
}
