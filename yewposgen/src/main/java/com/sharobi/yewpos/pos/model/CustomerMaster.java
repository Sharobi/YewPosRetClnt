package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;


public class CustomerMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

    private String name;

    private String code;

    private String address;

    private String pin;

    private int city;

    private int state;

    private int country;

    private String phoneNo;

    private String fax;

    private double obBal;

    private double creditLimit;

    private int storeId;

    private int companyId;

    private int isDeleted;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private String lang;

    private int finyrId;
    private String addharCardNo;
    private Date dob;
    private String gender;
    private int customerTypeId;
	private String customerTypeName;
	private String gstNo;
	private String dlNo;
	private String panNo;

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

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
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

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public String getAddharCardNo() {
		return addharCardNo;
	}

	public void setAddharCardNo(String addharCardNo) {
		this.addharCardNo = addharCardNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the customerTypeId
	 */
	public int getCustomerTypeId() {
		return customerTypeId;
	}

	/**
	 * @param customerTypeId the customerTypeId to set
	 */
	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	/**
	 * @return the customerTypeName
	 */
	public String getCustomerTypeName() {
		return customerTypeName;
	}

	/**
	 * @param customerTypeName the customerTypeName to set
	 */
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
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

	/**
	 * @return the dlNo
	 */
	public String getDlNo() {
		return dlNo;
	}

	/**
	 * @param dlNo the dlNo to set
	 */
	public void setDlNo(String dlNo) {
		this.dlNo = dlNo;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerMaster [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", pin="
				+ pin + ", city=" + city + ", state=" + state + ", country=" + country + ", phoneNo=" + phoneNo
				+ ", fax=" + fax + ", obBal=" + obBal + ", creditLimit=" + creditLimit + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", finyrId=" + finyrId + ", addharCardNo=" + addharCardNo + ", dob=" + dob
				+ ", gender=" + gender + ", customerTypeId=" + customerTypeId + ", customerTypeName=" + customerTypeName
				+ ", gstNo=" + gstNo + ", dlNo=" + dlNo + ", panNo=" + panNo + "]";
	}

}
