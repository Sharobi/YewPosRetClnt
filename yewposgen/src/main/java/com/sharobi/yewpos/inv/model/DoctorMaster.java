package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class DoctorMaster implements Serializable{
	
	private int id;

    private String name;

    private String code;

    private String qualification;

    private String speciality;

    private String address;

    private String pin;

    private String city;

    private String state;

    private String country;

    private String phoneNo;

    private String fax;

    private double opBal;

    private double commPer;

    private int isLocked;

    private int storeId;

    private int companyId;

    private int isDeleted;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private String lang;

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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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

	public double getOpBal() {
		return opBal;
	}

	public void setOpBal(double opBal) {
		this.opBal = opBal;
	}

	public double getCommPer() {
		return commPer;
	}

	public void setCommPer(double commPer) {
		this.commPer = commPer;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
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

	@Override
	public String toString() {
		return "DoctorMaster [id=" + id + ", name=" + name + ", code=" + code + ", qualification=" + qualification
				+ ", speciality=" + speciality + ", address=" + address + ", pin=" + pin + ", city=" + city + ", state="
				+ state + ", country=" + country + ", phoneNo=" + phoneNo + ", fax=" + fax + ", opBal=" + opBal
				+ ", commPer=" + commPer + ", isLocked=" + isLocked + ", storeId=" + storeId + ", companyId="
				+ companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang + "]";
	}
    
    

}
