package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private int id;
    
    private int expiryId;

    private String invNo;
    
    private String invDate;
    
    private int itemId;
    
    private String batchNo;
    
    private String expiryDate;
    
    private String expiryDateFormat;
    
    private int packUnitId;
    
    private double packQty;
   
    private int conversion;
    
    private double looseQty;
   
    private double freeQty;

    private double mrp;
   
    private double rate;
   
    private double amount;
    
    private int distributorId;
 
    private int finyrId;

    private int companyId;
    
    private int storeId;
    
    private String lang;
    private int locationId;
    private String mfdDate;
    private double purchaseRate;
    
    // new added 5.7.2019
    
    private double saleRate;
    private double discPer;
    private double disc;
    private int taxId;
    private int taxTypeId;
    private double taxPercentage;
    private double taxAmount;
    private double netAmount;
    private double totAmount;
    
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getPackUnitId() {
		return packUnitId;
	}

	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public double getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(double looseQty) {
		this.looseQty = looseQty;
	}

	public double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	/**
	 * @return the mfdDate
	 */
	public String getMfdDate() {
		return mfdDate;
	}

	/**
	 * @param mfdDate the mfdDate to set
	 */
	public void setMfdDate(String mfdDate) {
		this.mfdDate = mfdDate;
	}

	/**
	 * @return the purchaseRate
	 */
	public double getPurchaseRate() {
		return purchaseRate;
	}

	/**
	 * @param purchaseRate the purchaseRate to set
	 */
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	public double getDiscPer() {
		return discPer;
	}

	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}

	@Override
	public String toString() {
		return "ExpiryDetails [id=" + id + ", expiryId=" + expiryId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat="
				+ expiryDateFormat + ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion="
				+ conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate
				+ ", amount=" + amount + ", distributorId=" + distributorId + ", finyrId=" + finyrId + ", companyId="
				+ companyId + ", storeId=" + storeId + ", lang=" + lang + ", locationId=" + locationId + ", mfdDate="
				+ mfdDate + ", purchaseRate=" + purchaseRate + ", saleRate=" + saleRate + ", discPer=" + discPer
				+ ", disc=" + disc + ", taxId=" + taxId + ", taxTypeId=" + taxTypeId + ", taxPercentage="
				+ taxPercentage + ", taxAmount=" + taxAmount + ", netAmount=" + netAmount + ", totAmount=" + totAmount
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
}
