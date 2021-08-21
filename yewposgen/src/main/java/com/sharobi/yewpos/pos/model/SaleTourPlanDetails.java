package com.sharobi.yewpos.pos.model;
import java.io.Serializable;
import java.util.Date;
public class SaleTourPlanDetails {
	private static final long serialVersionUID = 1L;
	private int id;
	private int tourPlanId;
	private String tourPlanNo;
	private Date tourDate;
	private int cityId;
	private int zoneId;
	private int areaId;
	private long latitude;
	private long longitude;
	private int finyrId;
	private int storeId;
	private int companyId;
	private int customerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTourPlanId() {
		return tourPlanId;
	}
	public void setTourPlanId(int tourPlanId) {
		this.tourPlanId = tourPlanId;
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
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
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
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "SaleTourPlanDetails [id=" + id + ", tourPlanId=" + tourPlanId + ", tourPlanNo=" + tourPlanNo
				+ ", tourDate=" + tourDate + ", cityId=" + cityId + ", zoneId=" + zoneId + ", areaId=" + areaId
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", finyrId=" + finyrId + ", storeId="
				+ storeId + ", companyId=" + companyId + ", customerId=" + customerId + "]";
	}
	
	
}
