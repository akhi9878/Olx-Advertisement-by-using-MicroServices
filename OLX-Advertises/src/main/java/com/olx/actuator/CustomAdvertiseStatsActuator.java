package com.olx.actuator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.olx.repository.AdvertiseRepository;

@Component
@Endpoint(id = "advertise-stats" )  //http://localhost:9002/olx-monitor/advertise-stats
public class CustomAdvertiseStatsActuator {
	
	
	@Autowired
	AdvertiseRepository advertiseRepository;
	
	//advstats.put("No of Advertises by user name",Collections.singletonMap("Akhilesh",5));
	
	long noOfAdvertises = 0;
	long noOfActiveAdvertises = 0;
	long noOClosedAdvertises = 0;
	
	private static Map<String,Long> advstats = new HashMap<String,Long>();
	
	@PostConstruct
    public void initialize() {
		noOfAdvertises = this.advertiseRepository.findByCount();
		noOfActiveAdvertises = this.advertiseRepository.findActiveCount();
		noOClosedAdvertises = this.advertiseRepository.findClosedCount();
		
		advstats.put("No of Advertises",noOfAdvertises);
		advstats.put("No of Active Advertises",noOfActiveAdvertises);
		advstats.put("No of Closed Advertises",noOClosedAdvertises);
		
			
	}
	@ReadOperation
	public Map<String,Long> getAdvStats(){
		return advstats ;
		
	}
}
