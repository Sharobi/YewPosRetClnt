package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Expiry implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private int id;

    private String invNo;
   
    private String invDate;
    
    private String fromDate;
    
    private String toDate;
    
    private String remarks;
    
    private int isPosted;
   
    private int finyrId;
    
    private String finyrCode;

    private int companyId;
    
    private int storeId;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private String lang;
    
    private List<ExpiryDetails> expiryDetails;
    // new added 7.5.2019
    private double grossAmount;
    private double discAmount;
    private double taxAmount;
    private double roundoff;
    private double netAmount;
    private double totalMrp;
    private double cr_amount;
    private double dr_amount;
    private int cr_account_id;
    private int dr_account_id;
    private String expMode;
    private String qs;
 
    
    
	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	public List<ExpiryDetails> getExpiryDetails() {
		return expiryDetails;
	}

	public void setExpiryDetails(List<ExpiryDetails> expiryDetails) {
		this.expiryDetails = expiryDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public double getCr_amount() {
		return cr_amount;
	}

	public void setCr_amount(double cr_amount) {
		this.cr_amount = cr_amount;
	}

	public double getDr_amount() {
		return dr_amount;
	}

	public void setDr_amount(double dr_amount) {
		this.dr_amount = dr_amount;
	}

	public int getCr_account_id() {
		return cr_account_id;
	}

	public void setCr_account_id(int cr_account_id) {
		this.cr_account_id = cr_account_id;
	}

	public int getDr_account_id() {
		return dr_account_id;
	}

	public void setDr_account_id(int dr_account_id) {
		this.dr_account_id = dr_account_id;
	}

	

	public String getExpMode() {
		return expMode;
	}

	public void setExpMode(String expMode) {
		this.expMode = expMode;
	}

	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	@Override
	public String toString() {
		return "Expiry [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", remarks=" + remarks + ", isPosted=" + isPosted + ", finyrId=" + finyrId + ", finyrCode="
				+ finyrCode + ", companyId=" + companyId + ", storeId=" + storeId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", expiryDetails=" + expiryDetails + ", grossAmount=" + grossAmount
				+ ", discAmount=" + discAmount + ", taxAmount=" + taxAmount + ", roundoff=" + roundoff + ", netAmount="
				+ netAmount + ", totalMrp=" + totalMrp + ", cr_amount=" + cr_amount + ", dr_amount=" + dr_amount
				+ ", cr_account_id=" + cr_account_id + ", dr_account_id=" + dr_account_id + ", expMode=" + expMode
				+ ", qs=" + qs + "]";
	}

	

	

	


}
