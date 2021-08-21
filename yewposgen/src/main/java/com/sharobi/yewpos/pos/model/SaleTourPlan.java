package com.sharobi.yewpos.pos.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class SaleTourPlan {
	private static final long serialVersionUID = 1L;
	private int id;
	private String tourPlanNo;
	private Date tourDate;
    private int salesmanId;
	private int noOfCustomer;
	private int countryId;
	private int stateId;
	private String remarks;
	private int isCancel;
	private int isPosted;
	private int isDeleted;
	private int finyrId;
	private int storeId;
	private int companyId;
	private int createdBy;
	private Date createdDate;
	private int updatedBy;
	private Date updatedDate;
	private String finyrCode;
	private String cancelRemarks;
	private List<SaleTourPlanDetails> saleTourPlanDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTourPlanNo() {
		return tourPlanNo;
	}
	public void setTourPlanNo(String tourPlanNo) {
		this.tourPlanNo = tourPlanNo;
	}
	public Date getTourDate() {
		return tourDate;
	}
	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public int getNoOfCustomer() {
		return noOfCustomer;
	}
	public void setNoOfCustomer(int noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}
	public int getIsPosted() {
		return isPosted;
	}
	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
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
	public String getFinyrCode() {
		return finyrCode;
	}
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}
	public List<SaleTourPlanDetails> getSaleTourPlanDetails() {
		return saleTourPlanDetails;
	}
	public void setSaleTourPlanDetails(List<SaleTourPlanDetails> saleTourPlanDetails) {
		this.saleTourPlanDetails = saleTourPlanDetails;
	}
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}
	@Override
	public String toString() {
		return "SaleTourPlan [id=" + id + ", tourPlanNo=" + tourPlanNo + ", tourDate=" + tourDate + ", salesmanId="
				+ salesmanId + ", noOfCustomer=" + noOfCustomer + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", remarks=" + remarks + ", isCancel=" + isCancel + ", isPosted=" + isPosted + ", isDeleted="
				+ isDeleted + ", finyrId=" + finyrId + ", storeId=" + storeId + ", companyId=" + companyId
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", finyrCode=" + finyrCode + ", cancelRemarks=" + cancelRemarks
				+ ", saleTourPlanDetails=" + saleTourPlanDetails + "]";
	}
	
	
}
