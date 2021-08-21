package com.sharobi.yewpos.storemgnt.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author habib,Manodip
 *
 */
public class StoreMaster implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private CompanyMaster companyMaster;
	private String name;
	private Date registrationDate;
	private Date terminationDate;
	private int currencyId;
	private String latitude;
	private String longitude;
	private String address;
	private String state;
	private String country;
	private String postcode;
	private String email;
	private String phone;
	private String fax;
	private int isActive;
	private int isDeleted;
	private int createdBy;
	private Date createdDate;
	private int updatedBy;
	private Date updatedDate;
	private String lang;
	private int isDefault;
	private String dlLicenceNo;
    private Date dlRegistrationDate;
    private Date dlExpiryDate;
    private String stateLicenceNo;
    private Date stateRegistrationDate;
    private Date stateExpiryDate;
    private String taxRegNo;
    private double taxPer;
    private double vatPer;
    private int isMailEnable;
    private String mailPassword;
    private String mailPort;
    private String mailSmtp;
    private String taxRegNoText;
    private int isExclusive;
    private int isEsi;
    private String defaultPort;
	private int baudRate;
	private int numericKeyBoard;
	private int isCustomerDisplay ;
	private int isSalesman ;
	private int isConversion ;
	private int isManufacturer ;
	private int isFree ;
	private int isOnBillSale ;
	private int isOnBillPurchase ;
	private int isNewDisplay ;
	private int isMrpEnable;
	private int isSaleman;
	private int finYrId;


	public String getTaxRegNo() {
		return taxRegNo;
	}
	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}
	public double getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}
	public double getVatPer() {
		return vatPer;
	}
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}
	public String getDlLicenceNo() {
		return dlLicenceNo;
	}
	public void setDlLicenceNo(String dlLicenceNo) {
		this.dlLicenceNo = dlLicenceNo;
	}
	public Date getDlRegistrationDate() {
		return dlRegistrationDate;
	}
	public void setDlRegistrationDate(Date dlRegistrationDate) {
		this.dlRegistrationDate = dlRegistrationDate;
	}
	public Date getDlExpiryDate() {
		return dlExpiryDate;
	}
	public void setDlExpiryDate(Date dlExpiryDate) {
		this.dlExpiryDate = dlExpiryDate;
	}
	public String getStateLicenceNo() {
		return stateLicenceNo;
	}
	public void setStateLicenceNo(String stateLicenceNo) {
		this.stateLicenceNo = stateLicenceNo;
	}
	public Date getStateRegistrationDate() {
		return stateRegistrationDate;
	}
	public void setStateRegistrationDate(Date stateRegistrationDate) {
		this.stateRegistrationDate = stateRegistrationDate;
	}
	public Date getStateExpiryDate() {
		return stateExpiryDate;
	}
	public void setStateExpiryDate(Date stateExpiryDate) {
		this.stateExpiryDate = stateExpiryDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CompanyMaster getCompanyMaster() {
		return companyMaster;
	}
	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getIsMailEnable() {
		return isMailEnable;
	}
	public void setIsMailEnable(int isMailEnable) {
		this.isMailEnable = isMailEnable;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public String getMailSmtp() {
		return mailSmtp;
	}
	public void setMailSmtp(String mailSmtp) {
		this.mailSmtp = mailSmtp;
	}
	public String getTaxRegNoText() {
		return taxRegNoText;
	}
	public void setTaxRegNoText(String taxRegNoText) {
		this.taxRegNoText = taxRegNoText;
	}
	public int getIsExclusive() {
		return isExclusive;
	}
	public void setIsExclusive(int isExclusive) {
		this.isExclusive = isExclusive;
	}
	public int getIsEsi() {
		return isEsi;
	}
	public void setIsEsi(int isEsi) {
		this.isEsi = isEsi;
	}
	/**
	 * @return the defaultPort
	 */
	public String getDefaultPort() {
		return defaultPort;
	}
	/**
	 * @param defaultPort the defaultPort to set
	 */
	public void setDefaultPort(String defaultPort) {
		this.defaultPort = defaultPort;
	}
	/**
	 * @return the baudRate
	 */
	public int getBaudRate() {
		return baudRate;
	}
	/**
	 * @param baudRate the baudRate to set
	 */
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}
	/**
	 * @return the numericKeyBoard
	 */
	public int getNumericKeyBoard() {
		return numericKeyBoard;
	}
	/**
	 * @param numericKeyBoard the numericKeyBoard to set
	 */
	public void setNumericKeyBoard(int numericKeyBoard) {
		this.numericKeyBoard = numericKeyBoard;
	}

	/**
	 * @return the isCustomerDisplay
	 */
	public int getIsCustomerDisplay() {
		return isCustomerDisplay;
	}
	/**
	 * @param isCustomerDisplay the isCustomerDisplay to set
	 */
	public void setIsCustomerDisplay(int isCustomerDisplay) {
		this.isCustomerDisplay = isCustomerDisplay;
	}

	/**
	 * @return the isSalesman
	 */
	public int getIsSalesman() {
		return isSalesman;
	}
	/**
	 * @param isSalesman the isSalesman to set
	 */
	public void setIsSalesman(int isSalesman) {
		this.isSalesman = isSalesman;
	}
	/**
	 * @return the isConversion
	 */
	public int getIsConversion() {
		return isConversion;
	}
	/**
	 * @param isConversion the isConversion to set
	 */
	public void setIsConversion(int isConversion) {
		this.isConversion = isConversion;
	}
	/**
	 * @return the isManufacturer
	 */
	public int getIsManufacturer() {
		return isManufacturer;
	}
	/**
	 * @param isManufacturer the isManufacturer to set
	 */
	public void setIsManufacturer(int isManufacturer) {
		this.isManufacturer = isManufacturer;
	}
	/**
	 * @return the isFree
	 */
	public int getIsFree() {
		return isFree;
	}
	/**
	 * @param isFree the isFree to set
	 */
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	/**
	 * @return the isOnBillSale
	 */
	public int getIsOnBillSale() {
		return isOnBillSale;
	}
	/**
	 * @param isOnBillSale the isOnBillSale to set
	 */
	public void setIsOnBillSale(int isOnBillSale) {
		this.isOnBillSale = isOnBillSale;
	}
	/**
	 * @return the isOnBillPurchase
	 */
	public int getIsOnBillPurchase() {
		return isOnBillPurchase;
	}
	/**
	 * @param isOnBillPurchase the isOnBillPurchase to set
	 */
	public void setIsOnBillPurchase(int isOnBillPurchase) {
		this.isOnBillPurchase = isOnBillPurchase;
	}
	/**
	 * @return the isNewDisplay
	 */
	public int getIsNewDisplay() {
		return isNewDisplay;
	}
	/**
	 * @param isNewDisplay the isNewDisplay to set
	 */
	public void setIsNewDisplay(int isNewDisplay) {
		this.isNewDisplay = isNewDisplay;
	}


	/**
	 * @return the isMrpEnable
	 */
	public int getIsMrpEnable() {
		return isMrpEnable;
	}
	/**
	 * @param isMrpEnable the isMrpEnable to set
	 */
	public void setIsMrpEnable(int isMrpEnable) {
		this.isMrpEnable = isMrpEnable;
	}


	/**
	 * @return the isSaleman
	 */
	public int getIsSaleman() {
		return isSaleman;
	}
	/**
	 * @param isSaleman the isSaleman to set
	 */
	public void setIsSaleman(int isSaleman) {
		this.isSaleman = isSaleman;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public int getFinYrId() {
		return finYrId;
	}
	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}
	@Override
	public String toString() {
		return "StoreMaster [id=" + id + ", companyMaster=" + companyMaster + ", name=" + name + ", registrationDate="
				+ registrationDate + ", terminationDate=" + terminationDate + ", currencyId=" + currencyId
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", address=" + address + ", state=" + state
				+ ", country=" + country + ", postcode=" + postcode + ", email=" + email + ", phone=" + phone + ", fax="
				+ fax + ", isActive=" + isActive + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", isDefault=" + isDefault + ", dlLicenceNo=" + dlLicenceNo
				+ ", dlRegistrationDate=" + dlRegistrationDate + ", dlExpiryDate=" + dlExpiryDate + ", stateLicenceNo="
				+ stateLicenceNo + ", stateRegistrationDate=" + stateRegistrationDate + ", stateExpiryDate="
				+ stateExpiryDate + ", taxRegNo=" + taxRegNo + ", taxPer=" + taxPer + ", vatPer=" + vatPer
				+ ", isMailEnable=" + isMailEnable + ", mailPassword=" + mailPassword + ", mailPort=" + mailPort
				+ ", mailSmtp=" + mailSmtp + ", taxRegNoText=" + taxRegNoText + ", isExclusive=" + isExclusive
				+ ", isEsi=" + isEsi + ", defaultPort=" + defaultPort + ", baudRate=" + baudRate + ", numericKeyBoard="
				+ numericKeyBoard + ", isCustomerDisplay=" + isCustomerDisplay + ", isSalesman=" + isSalesman
				+ ", isConversion=" + isConversion + ", isManufacturer=" + isManufacturer + ", isFree=" + isFree
				+ ", isOnBillSale=" + isOnBillSale + ", isOnBillPurchase=" + isOnBillPurchase + ", isNewDisplay="
				+ isNewDisplay + ", isMrpEnable=" + isMrpEnable + ", isSaleman=" + isSaleman + ", finYrId=" + finYrId
				+ "]";
	}


}
