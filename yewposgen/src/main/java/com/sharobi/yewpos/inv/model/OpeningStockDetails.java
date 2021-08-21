package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OpeningStockDetails implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private int itemId;

    private String batchNo;

    private Date expiryDate;

	private String expiryDateFormat;

    private double packQty;

    private double mrp;

    private double rate;

    private String asOnDate;

    private int lastDistributorId;

    private int finyrId;

    private int packUnitId;

    private int conversion;

    private int looseQty;

    private int storeId;

    private int companyId;

    private int isDeleted;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private double vatPer;

    private double taxPer;

    private int taxId;

    private double taxPercentage;

    private double taxAmount;
    private int taxTypeId;

    private int isGroupTax;

    private String taxMode;

    private double saleRate;
    private double purchaseRate;
    private int looseUnitId;
    private double markup;
    private double listedMrp;
    private int retailTypeId;
    private int tmpMappingId;
    private int locationId;
    private String mfdDate;
    private List<InvOpeningStockDetails> invOpeningStockDetails;
    private int expiryMonth;


	/**
	 * @return the taxTypeId
	 */
	public int getTaxTypeId() {
		return taxTypeId;
	}

	/**
	 * @param taxTypeId the taxTypeId to set
	 */
	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	/**
	 * @return the saleRate
	 */
	public double getSaleRate() {
		return saleRate;
	}

	/**
	 * @param saleRate the saleRate to set
	 */
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	public double getVatPer() {
		return vatPer;
	}

	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}

	public double getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
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

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public int getIsGroupTax() {
		return isGroupTax;
	}

	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public String getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {}


	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLastDistributorId() {
		return lastDistributorId;
	}

	public void setLastDistributorId(int lastDistributorId) {
		this.lastDistributorId = lastDistributorId;
	}

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public int getPackUnitId() {
		return packUnitId;
	}

	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
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

	/**
	 * @return the looseUnitId
	 */
	public int getLooseUnitId() {
		return looseUnitId;
	}

	/**
	 * @param looseUnitId the looseUnitId to set
	 */
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}

	/**
	 * @return the markup
	 */
	public double getMarkup() {
		return markup;
	}

	/**
	 * @param markup the markup to set
	 */
	public void setMarkup(double markup) {
		this.markup = markup;
	}

	/**
	 * @return the listedMrp
	 */
	public double getListedMrp() {
		return listedMrp;
	}

	/**
	 * @param listedMrp the listedMrp to set
	 */
	public void setListedMrp(double listedMrp) {
		this.listedMrp = listedMrp;
	}

	/**
	 * @return the retailTypeId
	 */
	public int getRetailTypeId() {
		return retailTypeId;
	}

	/**
	 * @param retailTypeId the retailTypeId to set
	 */
	public void setRetailTypeId(int retailTypeId) {
		this.retailTypeId = retailTypeId;
	}

	/**
	 * @return the tmpMappingId
	 */
	public int getTmpMappingId() {
		return tmpMappingId;
	}

	/**
	 * @param tmpMappingId the tmpMappingId to set
	 */
	public void setTmpMappingId(int tmpMappingId) {
		this.tmpMappingId = tmpMappingId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the invOpeningStockDetails
	 */
	public List<InvOpeningStockDetails> getInvOpeningStockDetails() {
		return invOpeningStockDetails;
	}

	/**
	 * @param invOpeningStockDetails the invOpeningStockDetails to set
	 */
	public void setInvOpeningStockDetails(
			List<InvOpeningStockDetails> invOpeningStockDetails) {
		this.invOpeningStockDetails = invOpeningStockDetails;
	}


	/**
	 * @return the expiryMonth
	 */
	public int getExpiryMonth() {
		return expiryMonth;
	}

	/**
	 * @param expiryMonth the expiryMonth to set
	 */
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpeningStockDetails [id=" + id + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate="
				+ expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packQty=" + packQty + ", mrp=" + mrp
				+ ", rate=" + rate + ", asOnDate=" + asOnDate + ", lastDistributorId=" + lastDistributorId
				+ ", finyrId=" + finyrId + ", packUnitId=" + packUnitId + ", conversion=" + conversion + ", looseQty="
				+ looseQty + ", storeId=" + storeId + ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", vatPer=" + vatPer + ", taxPer=" + taxPer + ", taxId=" + taxId
				+ ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", taxTypeId=" + taxTypeId
				+ ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", saleRate=" + saleRate + ", purchaseRate="
				+ purchaseRate + ", looseUnitId=" + looseUnitId + ", markup=" + markup + ", listedMrp=" + listedMrp
				+ ", retailTypeId=" + retailTypeId + ", tmpMappingId=" + tmpMappingId + ", locationId=" + locationId
				+ ", mfdDate=" + mfdDate + ", invOpeningStockDetails=" + invOpeningStockDetails + ", expiryMonth="
				+ expiryMonth + "]";
	}



}
