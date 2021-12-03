package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value=" Statys DTO contain details about status id and status name")
public class Status {
	
	@ApiModelProperty(value ="Unique Identifier for the Status")
	private int id;
	
	@ApiModelProperty(value ="Status's Name")
	private String status;
	
	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public Status() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
