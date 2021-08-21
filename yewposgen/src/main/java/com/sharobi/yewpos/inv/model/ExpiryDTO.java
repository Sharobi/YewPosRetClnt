package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDTO implements Serializable{
	
	private int expiryId;

    private String invNo;
    
    private String invDate;
    
    private String fromDate;
    
    private String toDate;
    
    private String remarks;

    private int isPosted;

    private String lang;
    // new added 5.7.2019
    private double grossAmount;
    private double discAmount;
    private double taxAmount;
    private double roundoff;
    private double netAmount;
    private double totalMrp;
    private String expMode;

	public int getExpiryId() {
		return expiryId;
	}

	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
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


	public String getExpMode() {
		return expMode;
	}

	public void setExpMode(String expMode) {
		this.expMode = expMode;
	}

	@Override
	public String toString() {
		return "ExpiryDTO [expiryId=" + expiryId + ", invNo=" + invNo + ", invDate=" + invDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", remarks=" + remarks + ", isPosted=" + isPosted + ", lang=" + lang
				+ ", grossAmount=" + grossAmount + ", discAmount=" + discAmount + ", taxAmount=" + taxAmount
				+ ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp=" + totalMrp + ", expMode="
				+ expMode + "]";
	}

	

}
