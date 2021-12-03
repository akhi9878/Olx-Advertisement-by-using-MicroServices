package com.olx.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.olx.entity.JsonDocument;

public interface JwtRepository extends MongoRepository<JsonDocument, ObjectId> {

	@Query("{jwtToken:'?0'}")
	JsonDocument findItemByJwtToken(String jwtToken);
	
	JsonDocument findByJwtTokenEquals(String jwtToken);
}
