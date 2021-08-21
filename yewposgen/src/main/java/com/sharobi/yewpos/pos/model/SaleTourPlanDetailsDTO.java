package com.sharobi.yewpos.pos.model;
import java.util.Date;
public class SaleTourPlanDetailsDTO {
	private static final long serialVersionUID = 1L;
	private int tourPlanId;
	private String tourPlanNo;
	private Date tourDate;
	private int customerId;
	private String customerName;
	private String customerAddress;
	private int cityId;
	private String cityName;
	private int zoneId;
	private String zoneName;
	private int areaid;
	private String areaName;
	private String customerPhone;
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
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public int getAreaid() {
		return areaid;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	@Override
	public String toString() {
		return "SaleTourPlanDetailsDTO [tourPlanId=" + tourPlanId + ", tourPlanNo=" + tourPlanNo + ", tourDate="
				+ tourDate + ", customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", cityId=" + cityId + ", cityName=" + cityName + ", zoneId=" + zoneId
				+ ", zoneName=" + zoneName + ", areaid=" + areaid + ", areaName=" + areaName + ", customerPhone="
				+ customerPhone + "]";
	}
	
}
