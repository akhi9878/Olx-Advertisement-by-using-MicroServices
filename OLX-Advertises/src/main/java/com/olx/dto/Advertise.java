package com.olx.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Advertise DTO")
public class Advertise {
	
	@ApiModelProperty(value = "Unique Identifier for Advertise")
	private int id;
	
	@ApiModelProperty(value = "Title for Advertise (Mandatory)")
	private String title;
	
	@ApiModelProperty(value = "Price for Advertise")
	private double price;
	//@JsonIgnore
	
	@ApiModelProperty(value = "Description for Advertise (Mandatory)")
	private String description;
	@ApiModelProperty(value = "User Name ")
	private String username;
	
	@ApiModelProperty(value = "Date when Advertise is Created")
	private LocalDate createdDate;
	
	@ApiModelProperty(value = "Date when Advertise is Modified")
	private LocalDate modifiedDate;
	//@JsonIgnore    //Ignores statusId in Output
	
	@ApiModelProperty(value = "status Id of Advertise")
	private int statusId;
	
	@ApiModelProperty(value = "Category Id of Advertise")
	private int categoryId;
	
	@ApiModelProperty(value = "Category Name(comes from another table) based on Category Id of Advertise (Optional)")
	private String categoryName;
	
	@ApiModelProperty(value = "Status Name(comes from another table) based on Status Id of Advertise (Optional)")
	private String statusName;
	
	@ApiModelProperty(value = "Advertise is active or Inactive")
	private boolean active;
	
	@ApiModelProperty(value = " Advertise Posted By")
	private String postedBy;
	
	public Advertise() {
		super();
	}

	public Advertise(int id, String title, double price, String description, String username, LocalDate createdDate,
			LocalDate modifiedDate, int statusId, int categoryId, String categoryName, String statusName, boolean active,
			String postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.username = username;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.statusId = statusId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.statusName = statusName;
		this.active = active;
		this.postedBy = postedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	
}
