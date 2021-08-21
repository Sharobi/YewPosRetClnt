/**
 * 
 */
package com.sharobi.yewpos.inv.model;
 
import java.io.Serializable;
import java.util.Date;

public class SalesmanMaster  implements Serializable {

	private static final long serialVersionUID = 1L;

private int id;
	private String name;
	private String empId;
	private String address;
	private int countryId;
	private int stateId;
	private int cityId;
	private String pin;
	private String phoneNo;
	private String addharCardNo;
	private String panNo;
	private double commissionPercentage;
	private int isDeleted;
	private int companyId;
	private int storeId;
	private int createdBy;
	private Date createdDate;
	private int updatedBy;
	private Date updatedDate;
	private String lang;
	private int finyrID;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the addharCardNo
	 */
	public String getAddharCardNo() {
		return addharCardNo;
	}
	/**
	 * @param addharCardNo the addharCardNo to set
	 */
	public void setAddharCardNo(String addharCardNo) {
		this.addharCardNo = addharCardNo;
	}
	/**
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}
	/**
	 * @param panNo the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	/**
	 * @return the commissionPercentage
	 */
	public double getCommissionPercentage() {
		return commissionPercentage;
	}
	/**
	 * @param commissionPercentage the commissionPercentage to set
	 */
	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the finyrID
	 */
	public int getFinyrID() {
		return finyrID;
	}
	/**
	 * @param finyrID the finyrID to set
	 */
	public void setFinyrID(int finyrID) {
		this.finyrID = finyrID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SalesmanMaster [id=" + id + ", name=" + name + ", empId=" + empId + ", address=" + address
				+ ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId + ", pin=" + pin
				+ ", phoneNo=" + phoneNo + ", addharCardNo=" + addharCardNo + ", panNo=" + panNo
				+ ", commissionPercentage=" + commissionPercentage + ", isDeleted=" + isDeleted + ", companyId="
				+ companyId + ", storeId=" + storeId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang + ", finyrID="
				+ finyrID + "]";
	}

	
}