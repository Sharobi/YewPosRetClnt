package com.sharobi.yewpos.pos.model;

public class SaleTourPlanDTO {
	private static final long serialVersionUID = 1L;
	private int saleTourPlanId;
	private String tourPlanNo;
	private String tourDate;
	private int salesmanId;
	private String salesmanName;
	private int noOfCustomer;
	private int countryId;
	private String countryName;
	private int stateId;
	private String stateName;
	private String remarks;
	private int isCancel;
	private String cancelRemarks;
	private int isPosted;
	private String salesmanPhone;
	public int getSaleTourPlanId() {
		return saleTourPlanId;
	}
	public void setSaleTourPlanId(int saleTourPlanId) {
		this.saleTourPlanId = saleTourPlanId;
	}
	public String getTourPlanNo() {
		return tourPlanNo;
	}
	public void setTourPlanNo(String tourPlanNo) {
		this.tourPlanNo = tourPlanNo;
	}
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}
	public int getIsPosted() {
		return isPosted;
	}
	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}
	public String getSalesmanPhone() {
		return salesmanPhone;
	}
	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}
	@Override
	public String toString() {
		return "SaleTourPlanDTO [saleTourPlanId=" + saleTourPlanId + ", tourPlanNo=" + tourPlanNo + ", tourDate="
				+ tourDate + ", salesmanId=" + salesmanId + ", salesmanName=" + salesmanName + ", noOfCustomer="
				+ noOfCustomer + ", countryId=" + countryId + ", countryName=" + countryName + ", stateId=" + stateId
				+ ", stateName=" + stateName + ", remarks=" + remarks + ", isCancel=" + isCancel + ", cancelRemarks="
				+ cancelRemarks + ", isPosted=" + isPosted + ", salesmanPhone=" + salesmanPhone + "]";
	}
	
	
	
}
