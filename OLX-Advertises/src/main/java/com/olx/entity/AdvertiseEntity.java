package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "advertises")
public class AdvertiseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	private String title;
	
	@Column(name = "category")
	private int categoryId;
	
	@Column(name = "status")
	private int statusId;
	private double price;
	private String description;
	
//	@Column(name = "photo")
//	private Blob photo;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	@Column(name = "modified_date")
	private LocalDate modifiedDate;

	@Column(name = "active")
    private boolean active;
	
   	@Column(name="posted_by")
   	private String postedBy;

	private String username;

	public AdvertiseEntity() {
		super();
	}



	public AdvertiseEntity(int id, String title, int categoryId, int statusId, double price, String description,
			LocalDate createdDate, LocalDate modifiedDate, boolean active, String postedBy, String username) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.postedBy = postedBy;
		this.username = username;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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

	

	public boolean isActive() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", categoryId=" + categoryId + ", statusId="
				+ statusId + ", price=" + price + ", description=" + description + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", active=" + active + ", postedBy=" + postedBy + ", username="
				+ username + "]";
	}
	
	
	
	

}
