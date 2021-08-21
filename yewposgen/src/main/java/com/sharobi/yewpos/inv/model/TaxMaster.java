/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author habib
 *
 */
public class TaxMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private double percentage;
    private String description;
    private int isGroup;
    private String taxMode;
    private int isDeleted;
    private int companyId;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
    private List<TaxGrpDetailsMaster> taxGrpDetailsMasters;    
    
	public List<TaxGrpDetailsMaster> getTaxGrpDetailsMasters() {
		return taxGrpDetailsMasters;
	}
	public void setTaxGrpDetailsMasters(List<TaxGrpDetailsMaster> taxGrpDetailsMasters) {
		this.taxGrpDetailsMasters = taxGrpDetailsMasters;
	}
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
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	@Override
	public String toString() {
		return "TaxMaster [id=" + id + ", name=" + name + ", percentage="
				+ percentage + ", description=" + description + ", isGroup="
				+ isGroup + ", taxMode=" + taxMode + ", isDeleted=" + isDeleted
				+ ", companyId=" + companyId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}
    
    

}
