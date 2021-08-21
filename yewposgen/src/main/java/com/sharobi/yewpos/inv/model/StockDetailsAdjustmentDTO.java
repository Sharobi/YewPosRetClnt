/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *  
 */
public class StockDetailsAdjustmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int itemId;
	private String itemName;
	private String sku;
	private String hsnCode;
	private String batchNo;
	private String expiryDate;
	private String expiryDateFormat;
	private double packQty;
	private int conversion;
	private int looseQty;
	private int packUnitId;
	private String packUnitName;
	private String asOnDate;
	private double mrp;
	private double rate;
	private double saleRate;
	private int distributorId;
	private String distributorName;
	private int remainingLooseQty;
	private int isSale;
	private int companyId;
    private int storeId;
    private int finyrId;
    private int createdBy;
    private int taxId;
    private double taxPercentage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public int getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
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

	public String getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
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

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
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

	public int getRemainingLooseQty() {
		return remainingLooseQty;
	}

	public void setRemainingLooseQty(int remainingLooseQty) {
		this.remainingLooseQty = remainingLooseQty;
	}

	public int getIsSale() {
		return isSale;
	}

	public void setIsSale(int isSale) {
		this.isSale = isSale;
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

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	@Override
	public String toString() {
		return "StockDetailsAdjustmentDTO [id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", sku=" + sku + ", hsnCode=" + hsnCode + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", asOnDate=" + asOnDate + ", mrp=" + mrp + ", rate=" + rate + ", saleRate=" + saleRate + ", distributorId=" + distributorId + ", distributorName=" + distributorName + ", remainingLooseQty=" + remainingLooseQty + ", isSale=" + isSale + ", companyId=" + companyId + ", storeId=" + storeId + ", finyrId=" + finyrId + ", createdBy=" + createdBy + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + "]";
	}

}
