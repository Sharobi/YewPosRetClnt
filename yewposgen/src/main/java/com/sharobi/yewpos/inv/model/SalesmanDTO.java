/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Arunima Roy
 *
 * Nov 13, 2017
 */
public class SalesmanDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String empId;
	private String address;
	private int countryId;
	private String countryName;
	private int stateId;
	private String stateName;
	private int cityId;
	private String cityName;
	private String pin;
	private String phoneNo;
	private String addharCardNo;
	private String panNo;
	private double commissionPercentage;
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
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SalesmanDTO [id=" + id + ", name=" + name + ", empId=" + empId + ", address=" + address + ", countryId="
				+ countryId + ", countryName=" + countryName + ", stateId=" + stateId + ", stateName=" + stateName
				+ ", cityId=" + cityId + ", cityName=" + cityName + ", pin=" + pin + ", phoneNo=" + phoneNo
				+ ", addharCardNo=" + addharCardNo + ", panNo=" + panNo + ", commissionPercentage="
				+ commissionPercentage + "]";
	}


}
