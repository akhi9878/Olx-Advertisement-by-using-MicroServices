package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAdvertiseDeleteIdException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidTokenException;
import com.olx.repository.AdvertiseRepository;

@Service
public class AdvertisesServiceImpl implements AdvertisesService{
	
	@Autowired
	AdvertiseRepository advertiseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MasterDataDelegate masterDataDelegate;
	
	@Autowired
	LoginDelegate loginDelegate;
	
	@Autowired
	EntityManager entityManager;
	
	//Used to Convert List of AdvertiseEntity to  List of AdvertiseDto
	public List<Advertise> getAdvertiseDtoList(List<AdvertiseEntity> advertiseEntityList) {
		List<Advertise> advertiseDtoList = new ArrayList<Advertise>();
		
		for(AdvertiseEntity advertiseEntity : advertiseEntityList) {
			Advertise advertiseDto = this.modelMapper.map(advertiseEntity, Advertise.class);
			advertiseDto = addStatusNameandCategory(advertiseDto);
			advertiseDtoList.add(advertiseDto);
		}
		
		return advertiseDtoList;			
	}

	public Advertise addStatusNameandCategory(Advertise advertise) {
		String statusName = this.masterDataDelegate.findStatusById(advertise.getStatusId());
		String categoryName = this.masterDataDelegate.findCategoryById(advertise.getCategoryId());
		advertise.setStatusName(statusName);
		advertise.setCategoryName(categoryName);
		return advertise;		
	}
	
	@Override
	public List<Advertise> getAllAdvertises(String token) {
		System.out.println(token);
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		
		List<AdvertiseEntity> advertisesEntityList = this.advertiseRepository.findAll();

		return getAdvertiseDtoList(advertisesEntityList);
	}

	
	@Override
	public Advertise getAdvertiseById(String token, int advertiseId) {
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		Optional<AdvertiseEntity> optionaladvertiseEntity = advertiseRepository.findById(advertiseId);
		
		if(optionaladvertiseEntity.isPresent()) //if advertiseId matches return true
		{
			AdvertiseEntity advertiseEntity = optionaladvertiseEntity.get();
			Advertise advertiseDto = this.modelMapper.map(advertiseEntity,Advertise.class);
			advertiseDto = addStatusNameandCategory(advertiseDto);
			return advertiseDto;
		}
		
		//return null;
		throw new InvalidAdvertiseIdException(""+advertiseId);
	}
	

	@Override
	public Advertise getAdvertiseByUserById(String token, int advertiseId) {
		
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		
		Optional<AdvertiseEntity> optionaladvertiseEntity = advertiseRepository.findById(advertiseId);
		
		if(optionaladvertiseEntity.isPresent()) {
			AdvertiseEntity advertiseEntity = optionaladvertiseEntity.get();
			Advertise advertiseDto = this.modelMapper.map(advertiseEntity,Advertise.class);
			advertiseDto = addStatusNameandCategory(advertiseDto);
			return advertiseDto;
		}
		
		//return null;
		throw new InvalidAdvertiseIdException(""+advertiseId);
	}
	
	
	@Override
	public List<Advertise> getAdvertiseBySearch(String searchText) {
		List<AdvertiseEntity> advertisesEntityList = this.advertiseRepository.findBySearch(searchText);
		return getAdvertiseDtoList(advertisesEntityList);	
	}
	
	@Override
	public List<Advertise> getAdvertiseByFilter(String searchText, int category, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy,
			int startIndex, int records) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		
		 Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);
		 
		 Predicate finalPredicate = null;
		 
		 if(searchText != null && !"".equals(searchText)) {
			 Predicate predicateTitle = criteriaBuilder.like(rootEntity.get("title"),searchText);
			 finalPredicate = criteriaBuilder.and(predicateTitle);			 
		     Predicate predicateDescription = criteriaBuilder.like(rootEntity.get("description"),searchText);
		     finalPredicate = criteriaBuilder.and(predicateDescription);
		 }
		 Predicate predicateCategory = criteriaBuilder.like(rootEntity.get("categoryId"),searchText);
	     finalPredicate = criteriaBuilder.and(predicateCategory);
	    
	     if(postedBy != null && !"".equals(postedBy)) {
			 Predicate predicatePostedBy = criteriaBuilder.like(rootEntity.get("postBy"),postedBy);
		     finalPredicate = criteriaBuilder.and(predicatePostedBy);			 
	       }
		 
		 criteriaQuery.where(finalPredicate);
		 TypedQuery<AdvertiseEntity> query = entityManager.createQuery(criteriaQuery);
		 List<AdvertiseEntity> advertiseEntityList = query.getResultList();
		 
		 return getAdvertiseDtoList(advertiseEntityList);	
		
	}

	
	@Override
	public Advertise createNewAdvertise(Advertise advertise, String token) {
		System.out.println(token);
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		AdvertiseEntity advertiseEntity = this.modelMapper.map(advertise,AdvertiseEntity.class);
		advertiseEntity = advertiseRepository.save(advertiseEntity);
		Advertise advertiseDto = this.modelMapper.map(advertiseEntity,Advertise.class);
		advertiseDto = addStatusNameandCategory(advertiseDto);
//		String statusName = this.masterDataDelegate.findStatusById(advertise.getStatusId());
//		String categoryName = this.masterDataDelegate.findCategoryById(advertise.getCategoryId());
//		advertiseDto.setStatusName(statusName);
//		advertiseDto.setCategoryName(categoryName);		
		return advertiseDto;
	}


	@Override
	public Advertise updateAdvertise(int advertiseId, Advertise advertise, String token) {
		System.out.println(token);
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		Optional<AdvertiseEntity> optionalAdvertiseEntity = advertiseRepository.findById(advertiseId);
		if(optionalAdvertiseEntity.isPresent()){
			AdvertiseEntity advertiseEntity = optionalAdvertiseEntity.get();
			advertise.setId(advertiseId);
			advertiseEntity = this.modelMapper.map(advertise,AdvertiseEntity.class);
			advertiseEntity = advertiseRepository.save(advertiseEntity);
			Advertise advertiseDto = this.modelMapper.map(advertiseEntity,Advertise.class);
			//advertiseDto = addStatusNameandCategory(advertiseDto);
			return advertiseDto;	
		}
		
		//return null;
		throw new InvalidAdvertiseIdException(""+advertiseId);
		
	}

	@Override
	public boolean deleteAdvertiseById(int advertiseId, String token) {
		System.out.println(token);
		if(!loginDelegate.isValidToken(token)) {
			throw new InvalidTokenException(token);
		}
		Optional<AdvertiseEntity> optionalAdvertiseEntity  = advertiseRepository.findById(advertiseId);
		if(optionalAdvertiseEntity.isPresent()) {
			advertiseRepository.deleteById(advertiseId);
			return true;
		}
		//return false;	
		throw new InvalidAdvertiseDeleteIdException(" "+advertiseId);
				
	}


}
