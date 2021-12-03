package com.olx.entity;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

//import org.springframework.data.annotation.Id;

@Document(collection="tokens")
public class JsonDocument {
	
	@Id
	private ObjectId _id;
	
	public String jwtToken;

	public JsonDocument(ObjectId _id, String jwtToken) {
		super();
		this._id = _id;
		this.jwtToken = jwtToken;
	}

	public JsonDocument() {
		// TODO Auto-generated constructor stub
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
	

}
