/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 */
public class SaleReturnDetailsSerialMapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private int saleReturnId;
    private int saleReturnDetailsId;
    private int itemId;
    private String batchNo;
    private String expiryDate;
    private String uniqueIdentifierNo;
    private double qty;
    private String checkStatus;
    private int finyrId;
    private int companyId;
    private int storeId;
    private String expiryDateFormat;
    private int tmpMappingId;
    private int isChecked;
    private double mrp;
    private double purchaseRate;
    private double saleRate;
	
    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the saleReturnId
	 */
	public int getSaleReturnId() {
		return saleReturnId;
	}
	/**
	 * @param saleReturnId the saleReturnId to set
	 */
	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	/**
	 * @return the saleReturnDetailsId
	 */
	public int getSaleReturnDetailsId() {
		return saleReturnDetailsId;
	}
	/**
	 * @param saleReturnDetailsId the saleReturnDetailsId to set
	 */
	public void setSaleReturnDetailsId(int saleReturnDetailsId) {
		this.saleReturnDetailsId = saleReturnDetailsId;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the uniqueIdentifierNo
	 */
	public String getUniqueIdentifierNo() {
		return uniqueIdentifierNo;
	}
	/**
	 * @param uniqueIdentifierNo the uniqueIdentifierNo to set
	 */
	public void setUniqueIdentifierNo(String uniqueIdentifierNo) {
		this.uniqueIdentifierNo = uniqueIdentifierNo;
	}
	/**
	 * @return the qty
	 */
	public double getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}
	/**
	 * @return the checkStatus
	 */
	public String getCheckStatus() {
		return checkStatus;
	}
	/**
	 * @param checkStatus the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}
	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the expiryDateFormat
	 */
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	/**
	 * @param expiryDateFormat the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
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
	/**
	 * @return the isChecked
	 */
	public int getIsChecked() {
		return isChecked;
	}
	/**
	 * @param isChecked the isChecked to set
	 */
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}
	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(double mrp) {
		this.mrp = mrp;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleReturnDetailsSerialMapper [id=" + id + ", saleReturnId="
				+ saleReturnId + ", saleReturnDetailsId=" + saleReturnDetailsId
				+ ", itemId=" + itemId + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", uniqueIdentifierNo="
				+ uniqueIdentifierNo + ", qty=" + qty + ", checkStatus="
				+ checkStatus + ", finyrId=" + finyrId + ", companyId="
				+ companyId + ", storeId=" + storeId + ", expiryDateFormat="
				+ expiryDateFormat + ", tmpMappingId=" + tmpMappingId
				+ ", isChecked=" + isChecked + ", mrp=" + mrp
				+ ", purchaseRate=" + purchaseRate + ", saleRate=" + saleRate
				+ "]";
	}

}
