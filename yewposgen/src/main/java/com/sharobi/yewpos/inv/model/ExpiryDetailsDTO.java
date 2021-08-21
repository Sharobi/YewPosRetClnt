package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDetailsDTO implements Serializable{
	
	private int itemId;

    private String itemName;
    
    private String batchNo;
    
    private String expiryDate;
    
    private String expiryDateFormat;

    private int packUnitId;
    
    private String packUnitName;
    
    private double packQty;
    
    private int conversion;
   
    private double looseQty;
    
    private double freeQty;
    
    private double mrp;
    
    private double rate;
    
    private double amount;
    
    private int distributorId;
    
    private String distributorName;
    
    private String itemUniqueKey;
    
    private String lang;
    
    private String netContent;
    
    private double calculateLooseQty;
    
    private String stockQty;
    
    private String rackName;
    
    private int rackId;
    
    private int expiryId;
    
    private int expiryDetailsId;
    
    private String invNo;
    
    private Date invDate;
    
    //new added 5.7.2019
    private double saleRate;
    private double discPer;
    private double disc;
    private int taxId;
    private int taxTypeId;
    private double taxPercentage;
    private double taxAmount;
    private double totAmount;
    private double netAmount;
    private String mfdDate;
    private String sku;
    private String itemCode;

	public int getExpiryId() {
		return expiryId;
	}

	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
	}

	public int getExpiryDetailsId() {
		return expiryDetailsId;
	}

	public void setExpiryDetailsId(int expiryDetailsId) {
		this.expiryDetailsId = expiryDetailsId;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public String getNetContent() {
		return netContent;
	}

	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}

	public double getCalculateLooseQty() {
		return calculateLooseQty;
	}

	public void setCalculateLooseQty(double calculateLooseQty) {
		this.calculateLooseQty = calculateLooseQty;
	}

	public String getStockQty() {
		return stockQty;
	}

	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public int getRackId() {
		return rackId;
	}

	public void setRackId(int rackId) {
		this.rackId = rackId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	public int getPackUnitId() {
		return packUnitId;
	}

	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}

	public String getPackUnitName() {
		return packUnitName;
	}

	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
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

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}


	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getMfdDate() {
		return mfdDate;
	}

	public void setMfdDate(String mfdDate) {
		this.mfdDate = mfdDate;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Override
	public String toString() {
		return "ExpiryDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount="
				+ amount + ", distributorId=" + distributorId + ", distributorName=" + distributorName
				+ ", itemUniqueKey=" + itemUniqueKey + ", lang=" + lang + ", netContent=" + netContent
				+ ", calculateLooseQty=" + calculateLooseQty + ", stockQty=" + stockQty + ", rackName=" + rackName
				+ ", rackId=" + rackId + ", expiryId=" + expiryId + ", expiryDetailsId=" + expiryDetailsId + ", invNo="
				+ invNo + ", invDate=" + invDate + ", saleRate=" + saleRate + ", discPer=" + discPer + ", disc=" + disc
				+ ", taxId=" + taxId + ", taxTypeId=" + taxTypeId + ", taxPercentage=" + taxPercentage + ", taxAmount="
				+ taxAmount + ", totAmount=" + totAmount + ", netAmount=" + netAmount + ", mfdDate=" + mfdDate
				+ ", sku=" + sku + ", itemCode=" + itemCode + "]";
	}

	

	

	
    
	
}
