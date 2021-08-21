/**
 * 
 */
package com.sharobi.yewpos.inv.model;
 
import java.io.Serializable;
import java.util.Date;

public class MarketerMaster  implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String code;

	private String address;
	
	private String phone;

	private String fax;

	private String email;

	private String url;

	private String manufacturerUnit;

	private int companyId;

	private int isDeleted;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;

	private String lang;
	
	private String qryCondition;

	/**
	 * 
	 */
	public MarketerMaster() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MarketerMaster(int id, String name, String code, String address, String phone, String fax, String email,
			String url, String manufacturerUnit, int companyId, int isDeleted, int createdBy, Date createdDate,
			int updatedBy, Date updatedDate, String lang, String qryCondition) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.url = url;
		this.manufacturerUnit = manufacturerUnit;
		this.companyId = companyId;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.lang = lang;
		this.qryCondition=qryCondition;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getManufacturerUnit() {
		return manufacturerUnit;
	}

	public void setManufacturerUnit(String manufacturerUnit) {
		this.manufacturerUnit = manufacturerUnit;
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

	public String getQryCondition() {
		return qryCondition;
	}

	public void setQryCondition(String qryCondition) {
		this.qryCondition = qryCondition;
	}

	@Override
	public String toString() {
		return "MarketerMaster [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", phone=" + phone + ", fax=" + fax + ", fax=" + fax + ", email=" + email + ", url=" + url + ", manufacturerUnit=" + manufacturerUnit + ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang + ", qryCondition=" + qryCondition + "]";
	}
	
}