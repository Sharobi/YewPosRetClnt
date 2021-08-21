package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class RackMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int id;

    
    private String name;

    
    private int storeId;

    
    private int companyId;
    private int isDeleted;

    
    private int createdBy;

    
    private Date createdDate;

    
    private int updatedBy;

    
    private Date updatedDate;

    
    private String lang;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStoreId() {
		return storeId;
	}


	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public int getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public int getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	@Override
	public String toString() {
		return "RackMaster [id=" + id + ", name=" + name + ", storeId=" + storeId + ", companyId=" + companyId
				+ ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getStoreId()=" + getStoreId() + ", getCompanyId()="
				+ getCompanyId() + ", getIsDeleted()=" + getIsDeleted() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedDate()=" + getUpdatedDate() + ", getLang()=" + getLang() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

    
	
}
