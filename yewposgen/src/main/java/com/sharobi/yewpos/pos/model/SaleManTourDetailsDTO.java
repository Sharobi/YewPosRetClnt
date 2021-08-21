package com.sharobi.yewpos.pos.model;

public class SaleManTourDetailsDTO {
	private static final long serialVersionUID = 1L;
	private int tourPlanId;
	private int salesmanId;
	private String tourDate;
	private String tourTime;
	private String latitude;
	private String longitude;
	private String tourType;
	private String imeiNo;
	private String companyId;
	private String storeId;
	private String finyrId;
	public int getTourPlanId() {
		return tourPlanId;
	}
	public void setTourPlanId(int tourPlanId) {
		this.tourPlanId = tourPlanId;
	}
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public String getTourTime() {
		return tourTime;
	}
	public void setTourTime(String tourTime) {
		this.tourTime = tourTime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTourType() {
		return tourType;
	}
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(String finyrId) {
		this.finyrId = finyrId;
	}
	@Override
	public String toString() {
		return "SaleManTourDetailsDTO [tourPlanId=" + tourPlanId + ", salesmanId=" + salesmanId + ", tourDate="
				+ tourDate + ", tourTime=" + tourTime + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", tourType=" + tourType + ", imeiNo=" + imeiNo + ", companyId=" + companyId + ", storeId=" + storeId
				+ ", finyrId=" + finyrId + "]";
	}
	
	
}
