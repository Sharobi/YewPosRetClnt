/**
 *
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 27-Jul-2017
 */
public class CustomerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String code;
    private String address;
    private String pin;
    private String city;
    private String state;
    private String country;
    private String phoneNo;
    private String fax;
    private double obBal;
    private double creditLimit;
    private double paybleAmount;
    private String paybleText;
    private String addharCardNo;
    private double outstandingAmount;
    private String dob;
    private String gender;

    private String gstNo;

    private int cityId;
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
	private int stateId;
    private int countryId;

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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public double getObBal() {
		return obBal;
	}
	public void setObBal(double obBal) {
		this.obBal = obBal;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getPaybleAmount() {
		return paybleAmount;
	}
	public void setPaybleAmount(double paybleAmount) {
		this.paybleAmount = paybleAmount;
	}
	public String getPaybleText() {
		return paybleText;
	}
	public void setPaybleText(String paybleText) {
		this.paybleText = paybleText;
	}
	public String getAddharCardNo() {
		return addharCardNo;
	}
	public void setAddharCardNo(String addharCardNo) {
		this.addharCardNo = addharCardNo;
	}

	public double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the gstNo
	 */
	public String getGstNo() {
		return gstNo;
	}
	/**
	 * @param gstNo the gstNo to set
	 */
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", pin=" + pin
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", phoneNo=" + phoneNo + ", fax="
				+ fax + ", obBal=" + obBal + ", creditLimit=" + creditLimit + ", paybleAmount=" + paybleAmount
				+ ", paybleText=" + paybleText + ", addharCardNo=" + addharCardNo + ", outstandingAmount="
				+ outstandingAmount + ", dob=" + dob + ", gender=" + gender + ", gstNo=" + gstNo + ", cityId=" + cityId
				+ ", stateId=" + stateId + ", countryId=" + countryId + "]";
	}


}
